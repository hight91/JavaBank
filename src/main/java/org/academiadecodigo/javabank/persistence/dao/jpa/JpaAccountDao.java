package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.session.AbstractDAO;
import org.academiadecodigo.javabank.session.DAO;
import org.h2.engine.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaAccountDao extends AbstractDAO<Account> {

    public JpaAccountDao(EntityManagerFactory emf, Class<Account> modelType) {
        super(emf, modelType);
    }

}
