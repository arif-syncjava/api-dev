package com.arifsyncjava.apidev.earphone.config;

import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@EnableTransactionManagement
@EnableJpaRepositories (
        basePackages = "com.arifsyncjava.apidev.earphone.repository",
        entityManagerFactoryRef = "earphoneEntityManagerFactory",
        transactionManagerRef = "earphoneTransactionManager"
)
@Configuration
public class SchemaMigrationConfig {

    @Bean
    @ConfigurationProperties( prefix = "earphone.database")
    DataSourceProperties earPhoneDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    DataSource   earPhoneDataSource (
            @Qualifier ("earPhoneDataSourceProperties")
            DataSourceProperties earPhoneDataSourceProperties) {
        return earPhoneDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    EntityManagerFactoryBuilder earphoneEntityManagerFactoryBuilder () {

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");

        return new EntityManagerFactoryBuilder(jpaVendorAdapter,
               properties, null);

    }

    @Bean
    LocalContainerEntityManagerFactoryBean earphoneEntityManagerFactory (
            @Qualifier ("earphoneEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier ("earPhoneDataSource") DataSource earPhoneDataSource ) {

        return builder.dataSource(earPhoneDataSource)
                .packages("com.arifsyncjava.apidev.earphone.model")
                .build();
    }


    @Bean
    PlatformTransactionManager earphoneTransactionManager (
     @Qualifier ("earphoneEntityManagerFactory")   EntityManagerFactory earphoneEntityManagerFactory) {
        return new JpaTransactionManager(earphoneEntityManagerFactory);
    }




    @Bean
    public Flyway flywayConfig (@Qualifier ("earPhoneDataSource")
                                    DataSource earPhoneDataSource ) {
        Flyway flyway = Flyway.configure()
                .dataSource(earPhoneDataSource)
                .locations("classpath:/db.migration")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .load();


        flyway.migrate();


        return flyway;

    }




}
