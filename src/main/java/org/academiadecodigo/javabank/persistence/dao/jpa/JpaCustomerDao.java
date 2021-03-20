package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

public class JpaCustomerDao extends AbstractDAO<Customer> {

    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();

    public JpaCustomerDao(EntityManagerFactory emf, Class<Customer> modelType) {
        super(emf, Customer.class);
    }

    public Customer findByUsername(String username) {
        jpaTransactionManager.beginRead();
        return  jpaTransactionManager.getEm().find(Customer.class, username);
    }

    public Customer findByEmail(String email) {
        jpaTransactionManager.beginRead();
        return  jpaTransactionManager.getEm().find(Customer.class, email);
    }

}
