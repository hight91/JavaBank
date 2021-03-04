package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public class CustomerServices implements CustomerService{
    private Bank bank;


    public CustomerServices(Customer customer, Bank bank) {
        this.bank = bank;
    }

    /**
     * @param id the new bank customer
     * Gets the Customer of the bank customers
     * by its id
     *
     * @return customer
     */
    @Override
    public Customer get(Integer id) {
        return bank.getCustomers().get(id);
    }
    /**
     * Gets the list of the bank customers
     *
     * @return customer list
     */
    @Override
    public List<Customer> list() {
        return (List<Customer>) bank.getCustomers().values();
    }
    /**
     * Gets the ids of the bank customers
     *
     * @return customer ids
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return bank.getCustomers().keySet();
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */

    @Override
    public double getBalance(int customerId) {
        double balance = 0;

        for (Customer customer : bank.getCustomers().values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     */

    @Override
    public void add(Customer customer) {
        bank.getCustomers().put(customer.getId(), customer);
    }
}
