package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.persistence.JpaBootstrap;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.session.AbstractDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        JpaBootstrap jpa = new JpaBootstrap();
        EntityManagerFactory emf = jpa.start();
        JpaSessionManager jpm = new JpaSessionManager();
        jpm.setEmf(emf);

        App app = new App();
        app.bootStrap(emf, jpm);

        jpa.stop();

    }

    private void bootStrap(EntityManagerFactory emf, JpaSessionManager jpm) {

        Bootstrap bootstrap = new Bootstrap();
        AuthServiceImpl authService = new AuthServiceImpl();

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setSm(jpm);
        JpaCustomerService jpaCustomerService = new JpaCustomerService();
        JpaAccountService jpaAccountService = new JpaAccountService();

        JpaAccountDao jpaAccountDao = new JpaAccountDao(emf,Account.class);
        JpaCustomerDao jpaCustomerDao = new JpaCustomerDao(emf, Customer.class);

        jpaAccountDao.setJpaTransactionManager(jpaTransactionManager);
        jpaCustomerDao.setJpaTransactionManager(jpaTransactionManager);
        bootstrap.setAuthService(authService);
        bootstrap.setAccountService(jpaAccountService);
        bootstrap.setCustomerService(jpaCustomerService);
        jpaAccountService.setJpaAccountDao(jpaAccountDao);
        jpaCustomerService.setJpaCustomerDao(jpaCustomerDao);
        authService.setJpaCustomerDao(jpaCustomerDao);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
