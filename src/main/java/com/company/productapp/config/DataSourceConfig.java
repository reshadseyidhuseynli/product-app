package com.company.productapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean("productDS")
    @ConfigurationProperties("spring.datasource.product")
    public DataSource getDataSource(){
        return DataSourceBuilder.create()
                .build();
    }
}
