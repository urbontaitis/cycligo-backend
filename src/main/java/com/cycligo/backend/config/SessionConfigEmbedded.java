package com.cycligo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

/**
 * Created by Mindaugas Urbontaitis on 07/04/2017.
 * cycligo-rest-api
 */
@Configuration
@Profile("!redis")
public class SessionConfigEmbedded {

    @Bean
    public SessionRepositoryFilter<?> springSessionRepositoryFilter() {
        SessionRepositoryFilter<?> sessionRepositoryFilter = new SessionRepositoryFilter<>(new MapSessionRepository());
        sessionRepositoryFilter.setHttpSessionStrategy(new HeaderHttpSessionStrategy());
        return sessionRepositoryFilter;
    }
}
