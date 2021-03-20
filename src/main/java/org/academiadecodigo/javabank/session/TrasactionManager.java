package org.academiadecodigo.javabank.session;

public interface TrasactionManager {

     void beginRead();
     void beginWrite();
     void commit();
     void rollback();
}
