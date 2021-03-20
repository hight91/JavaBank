package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDAO<T extends Model> implements DAO<T> {


    private EntityManagerFactory emf;
    private Class<T> modelType;
    private  JpaTransactionManager jpaTransactionManager;


    public AbstractDAO(Class<T> modelType) {
        this.modelType = modelType;
    }

    @Override
    public List<T> findAll() {

        jpaTransactionManager.beginRead();
        EntityManager em = jpaTransactionManager.getEm();

        try {
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            criteriaQuery.select(root);
            return em.createQuery(criteriaQuery).getResultList();


        } finally {
            jpaTransactionManager.commit();
        }
    }

    @Override
    public T findById(Integer id) {
        EntityManager entityManager = jpaTransactionManager.getEm();
        try {
            jpaTransactionManager.beginRead();

           T find =  entityManager.find(modelType,id);
            return find;
        }finally {
            jpaTransactionManager.commit();
        }
    }

    @Override
    public T saveOrUpdate(T entity) {

        EntityManager em = jpaTransactionManager.getEm();
        try {
            jpaTransactionManager.beginWrite();
            T newEntity  = em.merge(entity);
            return newEntity;
        } catch (RollbackException ex) {

            em.getTransaction().rollback();

        } finally {
            jpaTransactionManager.commit();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    public JpaTransactionManager getJpaTransactionManager() {
        return jpaTransactionManager;
    }

    public void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager) {
        this.jpaTransactionManager = jpaTransactionManager;
    }
}
