package com.arifsyncjava.apidev.television.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

@Configuration
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

//    @Bean
//    @ConfigurationProperties (prefix = "television.database")
//    DataSource  televisionDataSource () {
//        return DataSourceBuilder.create()
//                .type(HikariDataSource.class)
//                .build();
//    }

    @Bean
    JdbcClient  televisionJdbcClient ( @Qualifier ("televisionDataSource")
                                       DataSource televisionDataSource ) {
        return JdbcClient.create(televisionDataSource);
    }









}
