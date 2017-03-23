package com.cycligo.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@Component
public class SocialDatabaseInitializer implements InitializingBean {

    private final DataSource dataSource;

    public SocialDatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void runScript(Resource resource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(resource);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassPathResource resource = new ClassPathResource("org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql");
        runScript(resource);
    }


}
