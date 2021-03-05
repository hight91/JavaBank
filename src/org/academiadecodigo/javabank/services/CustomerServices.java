package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public class CustomerServices implements CustomerService{
    private Bank bank;
    private AccountManager accountManager;


    public CustomerServices(Bank bank, AccountManager accountManager) {
        this.bank = bank;
        this.accountManager = accountManager;
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
    public Set<Integer> listCustomerAccountIds() {
        return  bank.getCustomerIds();
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

    /**
     * Gets the Customer id of the Customer
     *
     *
     * @return customerID
     */
    public Integer getId(){
       return bank.getLoginCustomer().getId();
    }




}
