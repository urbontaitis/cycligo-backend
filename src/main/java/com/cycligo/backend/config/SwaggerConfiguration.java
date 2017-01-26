package com.cycligo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    // Access swagger http://localhost:8080/api/swagger-ui.html

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }
}
