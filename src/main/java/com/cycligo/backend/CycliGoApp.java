package com.cycligo.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Mindaugas Urbontaitis on 13/11/2016. CycliGO - Built by cyclists for cyclists.
 */
@SpringBootApplication
@EnableScheduling
public class CycliGoApp extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return configureApplication(builder);
  }

  public static void main(String[] args) {
    configureApplication(new SpringApplicationBuilder()).run(args);
  }

  private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
    return builder.sources(CycliGoApp.class);
  }

}
