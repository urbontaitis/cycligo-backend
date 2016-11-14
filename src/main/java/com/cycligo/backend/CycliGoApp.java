package com.cycligo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Created by panda on 13/11/2016.
 */
@SpringBootApplication
public class CycliGoApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CycliGoApp.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
