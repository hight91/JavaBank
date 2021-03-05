package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;

public class AuthServices implements AuthService{

    private Bank bank;

    public AuthServices(Bank bank) {
        this.bank = bank;
    }
    /**
     * @param id from customer
     *checks if id in customers IDlist
     * @return boolean
     */
    @Override
    public boolean authenticate(Integer id) {
        bank.setLoginCustomer(id);
        return bank.getCustomers().containsKey(id);

        }

    @Override
    public Customer getAccessingCustomer() {
        return bank.getLoginCustomer();
    }

}
