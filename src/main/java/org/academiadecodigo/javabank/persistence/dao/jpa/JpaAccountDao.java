package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.session.TransactionManager;

public class JpaAccountDao extends AbstractDAO<AbstractAccount> {



    public JpaAccountDao(TransactionManager transactionManager) {
        super(AbstractAccount.class,  transactionManager);
    }

    @Override
    public void setTransactionManager(TransactionManager transactionManager) {

    }
}
