package com.arifsyncjava.apidev.pendrive.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;

@EnableTransactionManagement
@EnableJpaRepositories (
        basePackages = "com.arifsyncjava.apidev.pendrive.repository",
        entityManagerFactoryRef = "penDriveEntityManagerFactory",
        transactionManagerRef = "penDriveTransactionManager"
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
            @Qualifier ("penDriveDataSourceProperties")
            DataSourceProperties penDriveDataSourceProperties ) {
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
        builder.setPersistenceProvider(new HibernatePersistenceProvider());

        return builder;

    }

    @Bean
    PlatformTransactionManager  penDriveTransactionManager (
         @Qualifier ("penDriveEntityManagerFactory")   EntityManagerFactory penDriveEntityManagerFactory
    ) {
        return new JpaTransactionManager(penDriveEntityManagerFactory);
    }

    @Bean
    NamedParameterJdbcTemplate penDriveJdbcTemplate (
            @Qualifier ("penDriveDataSource")  DataSource penDriveDataSource ) {
        return new NamedParameterJdbcTemplate (penDriveDataSource);
    }

    @Bean
    DataSourceScriptDatabaseInitializer  penDriveSqlScript (
            @Qualifier ("penDriveDataSource")  DataSource penDriveDataSource) {

        var config = new DatabaseInitializationSettings();
        config.setMode(DatabaseInitializationMode.ALWAYS);
        config.setContinueOnError(false);
        config.setSchemaLocations(List.of(
                "classpath:/db.script/pendrive-schema.sql"));

        return new DataSourceScriptDatabaseInitializer(penDriveDataSource, config);


    }











}
