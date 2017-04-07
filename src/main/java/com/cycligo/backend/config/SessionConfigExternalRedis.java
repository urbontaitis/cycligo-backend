package com.cycligo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

/**
 * Created by Mindaugas Urbontaitis on 07/04/2017.
 * cycligo-rest-api
 */
@EnableRedisHttpSession
@Configuration
@Profile("redis")
public class SessionConfigExternalRedis {

    @Bean
    public HeaderHttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
}
