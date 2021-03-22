package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.session.TransactionManager;

public class JpaCustomerDao extends AbstractDAO<Customer> {

    TransactionManager jpaTransactionManager = new JpaTransactionManager();

    public JpaCustomerDao(TransactionManager transactionManager) {
        super(Customer.class, transactionManager);
    }

    public Customer findByUsername(String username) {
        jpaTransactionManager.beginRead();
        return  jpaTransactionManager.getEm().find(Customer.class, username);
    }

    public Customer findByEmail(String email) {
        jpaTransactionManager.beginRead();
        return  jpaTransactionManager.getEm().find(Customer.class, email);
    }

    @Override
    public void setTransactionManager(TransactionManager transactionManager) {
        this.jpaTransactionManager = transactionManager;
    }
}
