package com.arifsyncjava.apidev.earphone.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
    public Flyway flywayConfig (@Qualifier ("earPhoneDataSource")
                                    DataSource earPhoneDataSource ) {
        Flyway flyway = Flyway.configure()
                .dataSource(earPhoneDataSource)
                .locations("classpath:/db.migration")
                .load();

        flyway.migrate();

        return flyway;

    }




}
