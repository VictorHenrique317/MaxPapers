package com.maxpapers.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class PersistenceConfig{

    @Bean
    public BasicDataSource basicDataSource(
            @Value("${db.username}") String username,
            @Value("${db.password}") String password,
            @Value("${db.driverClassName}") String driverClassName,
            @Value("${db.url}") String url){

        log.info("Instantiating data source");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        return dataSource;
    }
}
