package com.arifsyncjava.apidev.television.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "television.database")
    DataSourceProperties televisionDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    DataSource  televisionDataSource( @Qualifier ("televisionDataSourceProperties")
            DataSourceProperties televisionDataSourceProperties) {
        return televisionDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager televisionTransactionManager (
            @Qualifier ("televisionDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    JdbcClient  televisionJdbcClient ( @Qualifier ("televisionDataSource")
                                       DataSource televisionDataSource ) {
        return JdbcClient.create(televisionDataSource);
    }









}
