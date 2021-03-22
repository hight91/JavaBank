package org.academiadecodigo.javabank.session;

import javax.persistence.EntityManager;

public interface TransactionManager {

     void beginRead();
     void beginWrite();
     void commit();
     void rollback();
     EntityManager getEm();
     void setSm(SessionManager sm);
     SessionManager getSm();
}
