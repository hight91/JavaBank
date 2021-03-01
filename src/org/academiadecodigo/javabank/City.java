package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class City {

    public static void main(String[] args) {
        Customer user = new Customer();
        AccountManager manager = new AccountManager();
        user.setAccountManager(manager);
        ATM atm = new ATM(manager);
        atm.run();





    }
}
