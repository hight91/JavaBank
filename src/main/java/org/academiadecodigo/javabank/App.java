package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.JpaBootstrap;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.session.SessionManager;
import org.academiadecodigo.javabank.session.TransactionManager;

import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        App app = new App();
        JpaBootstrap jpa = new JpaBootstrap();
        EntityManagerFactory emf = jpa.start();

        app.bootStrap(emf);
        jpa.stop();

    }

    private void bootStrap(EntityManagerFactory emf) {


        //INSTANCES
        Bootstrap bootstrap = new Bootstrap();


        Controller controller = bootstrap.wireObjects(emf);

        // start application
        controller.init();
    }
}
