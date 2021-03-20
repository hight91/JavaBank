package org.academiadecodigo.javabank.session;

import javax.persistence.EntityManager;

public interface SessionManager {

    void startSession();
    void stopSession();
    EntityManager getCurrentSession();
}
