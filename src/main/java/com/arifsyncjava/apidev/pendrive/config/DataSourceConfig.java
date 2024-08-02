package com.arifsyncjava.apidev.pendrive.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@EnableJpaRepositories (
        basePackages = "com.arifsyncjava.apidev.pendrive.repository",
        entityManagerFactoryRef = "penDriveEntityManagerFactory",
        transactionManagerRef = "bikeTransactionManager"
)
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties (prefix = "pendrive.database")
    DataSourceProperties penDriveDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    DataSource  penDriveDataSource (
            @Qualifier ("penDriveDataSourceProperties") DataSourceProperties penDriveDataSourceProperties ) {
        return penDriveDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean penDriveEntityManagerFactory (
          @Qualifier ("penDriveDataSource")  DataSource penDriveDataSource) {

        LocalContainerEntityManagerFactoryBean builder =
                new LocalContainerEntityManagerFactoryBean();

        builder.setDataSource(penDriveDataSource);
        builder.setPackagesToScan("com.arifsyncjava.apidev.pendrive.model");

        return builder;

    }

    @Bean
    PlatformTransactionManager  bikeTransactionManager (
         @Qualifier ("penDriveEntityManagerFactory")   EntityManagerFactory penDriveEntityManagerFactory
    ) {
        return new JpaTransactionManager(penDriveEntityManagerFactory);
    }

    @Bean
    NamedParameterJdbcTemplate bikeJdbcTemplate (
            @Qualifier ("penDriveDataSourceProperties")  DataSource penDriveDataSource ) {
        return new NamedParameterJdbcTemplate (penDriveDataSource);
    }









}
