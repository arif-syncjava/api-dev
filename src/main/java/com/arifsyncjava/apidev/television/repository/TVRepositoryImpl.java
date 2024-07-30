package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.television.model.Television;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public class TVRepositoryImpl implements TVRepository{

    private final JdbcClient jdbc;

    public TVRepositoryImpl(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    private final String TV_READ_QUERY =
            "SELECT brand,model,size,price From tvs WHERE model = :model ";


    @Override
    public Optional<Television> getTelevisionByModel(String model) {
        return jdbc.sql(TV_READ_QUERY)
                .param("model",model)
                .query(Television.class).optional();
    }



}
