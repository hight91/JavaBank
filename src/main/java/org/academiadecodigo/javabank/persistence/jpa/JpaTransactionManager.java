package org.academiadecodigo.javabank.persistence.jpa;

import org.academiadecodigo.javabank.session.SessionManager;
import org.academiadecodigo.javabank.session.TransactionManager;

import javax.persistence.EntityManager;

public class JpaTransactionManager implements TransactionManager {

    private SessionManager sm;

    @Override
    public void beginRead() {
        sm.startSession();
    }

    @Override
    public void beginWrite() {
        if(!(sm.getCurrentSession().getTransaction().isActive()) ){
            System.out.println("TRANSACTION IS CLOSED");
            sm.getCurrentSession().getTransaction().begin();
            return;
        }
        System.out.println(" TRANSACTION IS OPEN");
    }

    @Override
    public void commit() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            System.out.println("seesion active:");
            sm.getCurrentSession().getTransaction().commit();
        }
        System.out.println("stoping ssession");
        sm.stopSession();
    }

    @Override
    public void rollback() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().rollback();
        }
        sm.stopSession();
    }

    public EntityManager getEm() {
        return sm.getCurrentSession();
    }


    public void setSm(SessionManager sm) {
        this.sm = sm;
    }

    @Override
    public SessionManager getSm() {
        return sm;
    }
}