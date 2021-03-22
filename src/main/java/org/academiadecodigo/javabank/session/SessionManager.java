package org.academiadecodigo.javabank.session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface SessionManager {

    void startSession();
    void stopSession();
    EntityManager getCurrentSession();
    void setEmf(EntityManagerFactory emf);
}
