package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.session.TransactionManager;

import java.util.List;

public interface DAO<T extends Model> {

    // basic crud methods
    List<T> findAll();
    T findById(Integer id);
    T saveOrUpdate(T entity);
    void delete(Integer id);
    void close();
    TransactionManager getTM();


    void setTransactionManager(TransactionManager transactionManager);
}
