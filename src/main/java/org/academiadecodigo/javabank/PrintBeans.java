package org.academiadecodigo.javabank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class PrintBeans {
    @Autowired
    ApplicationContext applicationContext;

    public void printBeans() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
}