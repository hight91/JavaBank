package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.jpa.DAO;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A JPA {@link org.academiadecodigo.javabank.services.CustomerService} implementation
 */
public class CustomerServiceImpl implements org.academiadecodigo.javabank.services.CustomerService {

    private DAO<Customer> customerDAO;

    /**
     * @see org.academiadecodigo.javabank.services.CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

           Customer customer = Optional.ofNullable(customerDAO.findById(id)).orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));
            customerDAO.close();
            return customer.getAccounts().stream()
                    .mapToDouble(Account::getBalance).sum();



    }

    /**
     * @see org.academiadecodigo.javabank.services.CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

            Customer customer = Optional.ofNullable(customerDAO.findById(id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));
            customerDAO.close();
            return customer.getAccounts().stream()
                    .map(Model::getId)
                    .collect(Collectors.toSet());

    }

    @Override
    public void setCustomerDAO(DAO<Customer> customerDAO) {
        this.customerDAO = customerDAO;
    }

}
