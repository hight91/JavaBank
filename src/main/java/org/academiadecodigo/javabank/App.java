package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        ApplicationContext ac = new FileSystemXmlApplicationContext(
                new String[] {"src/main/resources/META-INF/springconfig.xml"}
        );

    // retrieve configured instance
        LoginController controller = ac.getBean("loginController", LoginController.class);
        controller.init();
    }

}
