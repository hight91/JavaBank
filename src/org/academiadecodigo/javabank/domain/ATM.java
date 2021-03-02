package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.;
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
    public void enterATM(Customer user){
        System.out.println("Hello "+ user.getClass().getSimpleName() +". Welcome to "+bank.getClass().getSimpleName());
        Prompt prompt = new Prompt(System.in, System.out);
        System.out.println("Choose your account");
        String[] options = listAccounts();
        MenuInputScanner scanner = new MenuInputScanner(options);
        int answerIndex = prompt.getUserInput(scanner);
        chooseAccounts(answerIndex);
    }
    public Account getAccount() {
        if (user.getAccounts().size() != 0) {
            return user.getAccounts().get(currentAccount);
        } else {
            return null;
        }
    }
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
    public String[] listAccounts(){
        if(user.getAccounts().size()==0){
            return new String[] {"Open Account", "Quit"};
        }else{
            System.out.println(user.getAccounts().size());

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
    public void run() {
        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Go back"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        int answerIndex = prompt.getUserInput(scanner);

        System.out.println("User wants to " + options[answerIndex - 1]);
        System.out.println("Processing ...");
        chooseOption(answerIndex);
    }
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

        System.out.println("Your account balance is: " + account.getBalance());

    }
    public void makeDeposit() {

            Double deposit = inputDoubles();
        System.out.println("I'm here");
        System.out.println(account.canCredit(deposit));
            if (account.canCredit(deposit)) {

                account.credit(deposit);
                System.out.println(deposit + " was deposited into your account!");
            } else {
                System.out.println("Deposit unsuccessful!");
                run();
            }
        }
    public double inputDoubles() {
        Prompt prompt = new Prompt(System.in, System.out);
        // accepts any double
        DoubleInputScanner anyDouble = new DoubleInputScanner();
        anyDouble.setMessage("Insert the value you wish: ");

        Double value = prompt.getUserInput(anyDouble);
        if (value > 0) {
            return value;
        } else {
            System.out.println("Please insert a number higher than 0");
            return inputDoubles();
        }
    }
    public void makeWithDrawal() {
        Double widthdrawal = inputDoubles();
        if (account.canWithdraw() && checkIfHasFunds(widthdrawal)) {
            account.debit(widthdrawal);
            System.out.println(widthdrawal + " was withdrawn!");
        } else {
            System.out.println("Operation failed!");
            run();
        }
    }
    public boolean checkIfHasFunds(double money){
            if (account.getBalance() - money <= 100.0) {
                System.out.println("You dont have sufficient funds to withdraw that amount");
                return false;
            } else {
                return true;
            }
        }
    public void openAccount(){

        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {"Create a Credit Account ", "Create a Debit Account","Go back"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Please select one option: ");
        int answerIndex = prompt.getUserInput(scanner);
        switch (answerIndex){
            case 1:
                user.openAccount(AccountType.CHECKING);
                setCurrentAccount(user.getAccounts().size());
                account = getAccount();
                System.out.println("Credit Account created!");
                break;
            case 2:
                user.openAccount(AccountType.SAVINGS);
                setCurrentAccount(user.getAccounts().size());
                user.getAccountManager().deposit(currentAccount, 100.0);
                account = getAccount();
                System.out.println("Debit Account created, with a deposit of 100€!");
                break;
            default:
                enterATM(user);
    }
    run();
    }
    public void quit(){
        System.out.println("Have a nice day");
    };
//SETTER
    public void setCurrentAccount(int account) {
        this.currentAccount = account;
    }



}
