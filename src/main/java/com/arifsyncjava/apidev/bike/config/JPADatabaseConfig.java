package com.arifsyncjava.apidev.bike.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;

@EnableTransactionManagement

@EnableJpaRepositories (
        basePackages = "com.arifsyncjava.apidev.bike.repository",
        entityManagerFactoryRef = "bikeEntityManagerFactory",
        transactionManagerRef = "bikeTransactionManager"
)
@Configuration
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

//    @Bean (name ="bikeEntityManagerFactoryBuilder" )
//    public EntityManagerFactoryBuilder  bikeEntityManagerFactoryBuilder () {
//
//        return new EntityManagerFactoryBuilder(jpaVendorAdapter,
//                Collections.emptyMap(), null);
//    }



    @Bean
    public LocalContainerEntityManagerFactoryBean bikeEntityManagerFactory (
            @Qualifier ("bikeDataSource") DataSource bikeDataSource ) {

        LocalContainerEntityManagerFactoryBean builder  =
                new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        builder.setDataSource(bikeDataSource);
        builder.setPackagesToScan("com.arifsyncjava.apidev.bike.model");
        builder.setJpaVendorAdapter(jpaVendorAdapter);
        builder.setPersistenceProvider(new HibernatePersistenceProvider());

        return builder;

    }

    @Bean
    public PlatformTransactionManager bikeTransactionManager (
           @Qualifier ("bikeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
