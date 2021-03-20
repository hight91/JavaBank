package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManagerFactory;

public class JpaAccountDao extends AbstractDAO<AbstractAccount> {

    public JpaAccountDao(EntityManagerFactory emf, Class<AbstractAccount> abstractAccountClass) {
        super(emf, AbstractAccount.class);
    }

}
