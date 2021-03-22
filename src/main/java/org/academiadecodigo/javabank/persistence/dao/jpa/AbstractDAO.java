package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.session.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDAO<T extends Model> implements DAO<T> {

    private Class<T> modelType;
    private TransactionManager transactionManager;

    public AbstractDAO(Class<T> modelType, TransactionManager transactionManager) {

        this.transactionManager = transactionManager;
        this.modelType = modelType;
    }

    @Override
    public List<T> findAll() {

        EntityManager em = transactionManager.getEm();

            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            criteriaQuery.select(root);
            return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {
        EntityManager entityManager = transactionManager.getEm();
        try {
            transactionManager.beginRead();
            T find = entityManager.find(modelType, id);
            return find;
        }catch (RollbackException ex) {

            entityManager.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public T saveOrUpdate(T entity) {

        EntityManager em = transactionManager.getEm();

        try {
            transactionManager.beginWrite();
            return  em.merge(entity);
        } catch (RollbackException ex) {

            em.getTransaction().rollback();
        }
        System.out.println("retunring null ? ");
     return null;
    }

    @Override
    public void delete(Integer id) {

    }

    public void close(){
        transactionManager.getEm().close();
        transactionManager.commit();
    }

    public TransactionManager getTM() {
        return transactionManager;
    }

}
