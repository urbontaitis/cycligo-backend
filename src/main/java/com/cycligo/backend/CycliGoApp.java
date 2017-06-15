package com.cycligo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * Created by Mindaugas Urbontaitis on 13/11/2016.
 * CycliGO - Built by cyclists for cyclists.
 */
@SpringBootApplication
@EnableScheduling
//@EnableOAuth2Client
//@EnableAuthorizationServer
public class CycliGoApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CycliGoApp.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

//    @Bean
//    CommandLineRunner init(final AccountRepository accountRepository) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                accountRepository.createAccount(new Account("mindaugas", "test123", "", ""));
//            }
//        };
//    }
}
