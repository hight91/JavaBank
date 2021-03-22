package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.session.TransactionManager;

import java.util.List;

public class JpaRecipientDao implements DAO {
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Model findById(Integer id) {
        return null;
    }

    @Override
    public Model saveOrUpdate(Model user) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void close() {

    }

    @Override
    public TransactionManager getTM() {
        return null;
    }

    @Override
    public void setTransactionManager(TransactionManager transactionManager) {

    }
}
