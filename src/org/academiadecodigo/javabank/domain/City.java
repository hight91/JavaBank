package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

public class City {

    public static void main(String[] args) {
        Customer francisco = new Customer();
        AccountManager manager = new AccountManager();
        Bank bancaralho = new Bank(manager);
        bancaralho.addCustomer(francisco);
        ATM atm = new ATM(bancaralho, francisco);
        atm.enterATM(francisco);
    }
}
