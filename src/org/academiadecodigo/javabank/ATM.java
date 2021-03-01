package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class ATM {

    private Account account;
    private AccountManager manager;

    public ATM(AccountManager manager) {
        this.manager = manager;
        account = null;
    }

    public void run() {
        int  account = inputInt();
        //checkifUserExists();
        Prompt prompt = new Prompt(System.in, System.out);

        String[] options = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Welcome to Javard Bank");

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
                quit();
                System.out.println("return");
                return;


        }
        run();
    }

    public boolean  checkifAccountExists(){
        if()
    }

    public boolean checkIfAccountCreated() {
        if (account != null) {
            return true;
        } else {
            System.out.println("Open a bank account first, please!");
            return false;
        }
    }

    public void getBalance() {

        if (checkIfAccountCreated()) {
            System.out.println("Hello client nº " + account.getId() + "\n your account balance is: " + account.getBalance());
        }

    }


    public void makeDeposit() {
        if (checkIfAccountCreated()) {
            Double deposit = inputDoubles();
            if (account.canCredit(deposit)) {
                account.credit(deposit);
                System.out.println(deposit + " was deposited into your account!");
            } else {
                System.out.println("Deposit unsuccessful!");
                run();
            }
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
    public int inputInt() {
        Prompt prompt = new Prompt(System.in, System.out);
        // accepts any int
        IntegerInputScanner anyInt = new IntegerInputScanner();
        anyInt.setMessage("Insert the value you wish: ");

        Integer value = prompt.getUserInput(anyInt);
        if (value > 0) {
            return value;
        } else {
            System.out.println("Please insert a number higher than 0");
            return inputInt();
        }
    }



    public void makeWithDrawal() {
        if (checkIfAccountCreated()) {
            Double widthdrawal = inputDoubles();
            if (account.canWithdraw() && checkIfHasFunds(widthdrawal)) {
                account.debit(widthdrawal);
                System.out.println(widthdrawal + " was withdrawn!");
            }
        } else {
            System.out.println("Operation failed!");
            run();
        }
    }

    public boolean checkIfHasFunds(double widthdrawal) {
        if (account.getBalance() - widthdrawal <= 100.0) {
            System.out.println("You dont have sufficient funds to withdraw that amount");
            return false;
        }
        return true;
    }

    public void openAccount(){

        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {"Create a Credit Account ", "Create a Debit Account","Go back"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Please select one option: ");
        int answerIndex = prompt.getUserInput(scanner);
        switch (answerIndex){
            case 1:
                manager.openAccount(AccountType.CHECKING);
                System.out.println("Credit Account created!");
            case 2:
                manager.openAccount(AccountType.SAVINGS);
                System.out.println("Debit Account created!");
            default:
                run();

    }}

    public void quit(){
        System.out.println("Have a nice day");
    };


}
