package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.exceptions.ApiException;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.request.CreateRequest;
import org.springframework.dao.DataAccessException;
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

    @Override
    public Television saveTelevision(Television television) {

        try {
            jdbc.sql("INSERT INTO tvs(brand,model,price,size) VALUES (:brand,:model,:price,:size)")
                    .param("brand",television.getBrand())
                    .param("model",television.getModel())
                    .param("price",television.getPrice())
                    .param("size",television.getSize())
                    .update();

            return jdbc.sql(TV_READ_QUERY)
                    .param("model",television.getModel())
                    .query(Television.class).single();

        } catch (DataAccessException exception) {
            throw new ApiException("An error occurred. Please try again.");

        }


    }


}
