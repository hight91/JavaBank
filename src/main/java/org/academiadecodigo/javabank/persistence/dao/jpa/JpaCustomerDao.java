package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.session.AbstractDAO;
import org.academiadecodigo.javabank.session.DAO;
import org.academiadecodigo.javabank.session.SessionManager;
import org.h2.engine.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
