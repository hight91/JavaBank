package org.academiadecodigo.javabank.session;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.services.CRUDService;
import org.h2.engine.User;

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


    public AbstractDAO(EntityManagerFactory emf, Class<T> modelType) {
        this.emf = emf;
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
        try {
            jpaTransactionManager.beginRead();
            EntityManager entityManager = jpaTransactionManager.getEm();
          //1  System.out.println(entityManager);
            return  entityManager.find(modelType, id);
        }finally {
            jpaTransactionManager.commit();
        }
    }

    @Override
    public T saveOrUpdate(T entity) {
        EntityManager em = jpaTransactionManager.getEm();
        jpaTransactionManager.beginRead();
        try {
            jpaTransactionManager.beginWrite();
            em.merge(entity);
            entity.getId();
            System.out.println(entity.getId());
            return findById(entity.getId());
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
