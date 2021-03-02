package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

public class ATM {

    private Account account;
    private int currentAccount;
    private Customer user;
    private Bank bank;

    public ATM(Bank bank, Customer user) {
        this.bank = bank;
        this.user = user;
    }
    //ENTER ATM
    public void enterATM(Customer user){
        System.out.println(Messages.WELCOME);
        Prompt prompt = new Prompt(System.in, System.out);
        System.out.println(Messages.CHOOSE_OPTION);
        String[] options = listAccounts();
        MenuInputScanner scanner = new MenuInputScanner(options);
        int answerIndex = prompt.getUserInput(scanner);
        chooseAccounts(answerIndex);
    }
    //MENU OPTIONS
    public void run() {
        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {Messages.VIEW_BALANCE, Messages.DEPOSIT, Messages.WIDTHDRAW, Messages.OPEN_ACCOUNT, Messages.GO_BACK};
        MenuInputScanner scanner = new MenuInputScanner(options);
        int answerIndex = prompt.getUserInput(scanner);

        System.out.println(Messages.USER_WANTS + options[answerIndex - 1]);
        System.out.println(Messages.PROCESSING);
        chooseOption(answerIndex);
    }
    //MANAGE ACCOUNTS
    public void chooseAccounts(int answerIndex){
        if(answerIndex <= user.getAccounts().size()){
            setCurrentAccount(answerIndex);
            run();
        }else if(answerIndex == user.getAccounts().size()+1){
            openAccount();
        }else{
            quit();
        }
    }
    public Account getAccount() {
        if (user.getAccounts().size() != 0) {
            return user.getAccounts().get(currentAccount);
        } else {
            return null;
        }
    }
    public String[] listAccounts(){
        if(user.getAccounts().size()==0){
            return new String[] {"Open Account", "Quit"};
        }else{
            String[] userAccounts = new String[user.getAccounts().size()+2];
            int counter = 0;
            for(int account  : user.getAccounts().keySet()){
                userAccounts[counter] = "Account nº: #"+ (account);
                counter++;
            }
            userAccounts[counter] = "New Account";
            userAccounts[counter+1] = "Quit";

            return userAccounts;
        }}
    public void openAccount(){

        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {Messages.CREDIT, Messages.DEBIT,Messages.GO_BACK};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage(Messages.CHOOSE_OPTION);
        int answerIndex = prompt.getUserInput(scanner);
        switch (answerIndex){
            case 1:
                user.openAccount(AccountType.CHECKING);
                setCurrentAccount(user.getAccounts().size());
                account = getAccount();
                System.out.println(Messages.ACCOUNT_CREATED);
                break;
            case 2:
                user.openAccount(AccountType.SAVINGS);
                setCurrentAccount(user.getAccounts().size());
                user.getAccountManager().deposit(currentAccount, 100.0);
                account = getAccount();
                System.out.println(Messages.ACCOUNT_CREATED+", with a deposit of 100€!");
                break;
            default:
                enterATM(user);
        }
        run();
    }
    //ACCOUNT OPTIONS
    public void chooseOption(int option) {

        switch (option) {
            case 1:
                getBalance();
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithDrawal();
                break;
            case 4:
                openAccount();
                break;
            default:
                enterATM(user);
                return;
        }
        run();
    }
    public void getBalance() {

        System.out.println(Messages.ACCOUNT_BALANCE + account.getBalance());

    }
    public void makeDeposit() {

        Double deposit = inputDoubles();
        if (account.canCredit(deposit)) {
            account.credit(deposit);
            System.out.println(deposit + Messages.WAS_DEPOSITED);
        } else {
            System.out.println(Messages.OPERATION_UNSUCCESSFUL);
            run();
        }
    }
    public void makeWithDrawal() {
        Double widthdrawal = inputDoubles();
        if (account.canWithdraw() && checkIfHasFunds(widthdrawal)) {
            account.debit(widthdrawal);
            System.out.println(widthdrawal + Messages.WAS_WIDTHDRAWN);
        } else {
            System.out.println(Messages.OPERATION_UNSUCCESSFUL);
            run();
        }
    }
    public boolean checkIfHasFunds(double money){
        if (account.getBalance() - money <= 100.0) {
            System.out.println(Messages.INSUFFICIENT_FUNDS);
            return false;
        } else {
            return true;
        }
    }
    //ACCOUNT SETTERS
    public void setCurrentAccount(int account) {
        this.currentAccount = account;
    }
    //AUXILIARY METHODS
    public double inputDoubles() {
        Prompt prompt = new Prompt(System.in, System.out);
        // accepts any double
        DoubleInputScanner anyDouble = new DoubleInputScanner();
        anyDouble.setMessage(Messages.INSERT_VALUE);

        Double value = prompt.getUserInput(anyDouble);
        if (value > 0) {
            return value;
        } else {
            System.out.println(Messages.INSERT_HIGHER_THAN_0);
            return inputDoubles();
        }
    }
    public void quit(){
        System.out.println(Messages.BYE);
    };


}
