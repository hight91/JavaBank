package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.jpa.DAO;

import java.util.Optional;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private Integer accessingCustomerId;
    private DAO<Customer> customerDAO;
    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Optional<Customer> customer = Optional.ofNullable(customerDAO.findById(id));

        customer.ifPresent(customer1 -> accessingCustomerId = customer1.getId());

        return customer.isPresent();

    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return customerDAO.findById(accessingCustomerId);
    }

    @Override
    public void setCustomerDao(DAO<Customer> customerDAO) {
        this.customerDAO = customerDAO;
    }
}
