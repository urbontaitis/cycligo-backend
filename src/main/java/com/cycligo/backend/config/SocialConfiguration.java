package com.cycligo.backend.config;

import com.cycligo.backend.authorisation.AuthUtil;
import com.cycligo.backend.authorisation.SimpleSignInAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.web.SignInAdapter;

import javax.sql.DataSource;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@Configuration
public class SocialConfiguration {

    @Bean
    public SocialConfigurer socialConfigurerAdapter(DataSource dataSource) {
        return new DatabaseSocialConfigurer(dataSource);
    }

    @Bean
    public SignInAdapter authSignInAdapter() {
        return new SimpleSignInAdapter(new HttpSessionRequestCache());
    }

}
