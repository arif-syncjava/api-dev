package com.arifsyncjava.apidev.bike;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableJpaRepositories (
        basePackages = "com.arifsyncjava.apidev.bike.repository",
        entityManagerFactoryRef = "bikeEntityManagerFactory",
        transactionManagerRef = "bikeTransactionManager"
)
@EnableTransactionManagement
public class JPADatabaseConfig {


    @Bean
    @ConfigurationProperties(prefix = "bike.database")
    DataSourceProperties bikeDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    DataSource bikeDataSource (
            @Qualifier("bikeDataSourceProperties")
            DataSourceProperties bikeDataSourceProperties ) {
        return bikeDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    EntityManagerFactoryBuilder  entityManagerFactoryBuilder () {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),
                Collections.emptyMap(), null);
    }



    @Bean
    public LocalContainerEntityManagerFactoryBean bikeEntityManagerFactory (
           EntityManagerFactoryBuilder builder,
            @Qualifier ("bikeDataSource") DataSource bikeDataSource ) {



        return builder.dataSource(bikeDataSource)
                .packages("com.arifsyncjava.apidev.bike.model")
                .persistenceUnit("bike")
                .build();
    }

    @Bean
    public PlatformTransactionManager bikeTransactionManager (
           @Qualifier ("bikeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }















}
