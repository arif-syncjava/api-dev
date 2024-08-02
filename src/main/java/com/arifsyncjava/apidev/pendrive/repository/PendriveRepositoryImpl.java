package com.arifsyncjava.apidev.pendrive.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PendriveRepositoryImpl {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PendriveJpaRepository pendriveJpaRepository;

    public PendriveRepositoryImpl (
          @Qualifier ("bikeJdbcTemplate")  NamedParameterJdbcTemplate jdbcTemplate,
          PendriveJpaRepository pendriveJpaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pendriveJpaRepository = pendriveJpaRepository;
    }









}
