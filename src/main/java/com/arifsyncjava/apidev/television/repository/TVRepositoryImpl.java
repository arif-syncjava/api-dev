package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.exceptions.ApiException;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.television.model.Television;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCountCallbackHandler;
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

    private final String TV_READ_QUERY = "SELECT brand,model,size,price From tvs WHERE model = :model ";
    private final String TV_SAVE_QUERY = "INSERT INTO tvs(brand,model,price,size) VALUES (:brand,:model,:price,:size)";
  private final String TV_UPDATE_QUERY = "UPDATE tvs SET brand = :brand, model = :model,price=:price,size=:size WHERE model = :model";

    @Override
    public Optional<Television> getTelevisionByModel(String model) {
        return jdbc.sql(TV_READ_QUERY)
                .param("model",model)
                .query(Television.class).optional();
    }

    @Override
    public Television saveTelevision(Television television) {

        if (countModel(television.getModel().trim().toLowerCase()) > 0) {
            throw new ApiException("model already taken. try a new model");
        }

        try {
            jdbc.sql(TV_SAVE_QUERY)
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



    @Override
    public Television updateTelevision(Television television) {

        try {
            jdbc.sql(TV_UPDATE_QUERY)

                    .param("brand", television.getBrand())
                    .param("model", television.getModel())
                    .param("price", television.getPrice())
                    .param("size", television.getSize())
                    .update();

            return jdbc.sql(TV_READ_QUERY)
                    .param("model", television.getModel())
                    .query(Television.class).single();

        } catch (DataAccessException exception) {
            throw new ApiException("An error occurred. Please try again.");

        }

    }

    @Override
    public void deleteTelevision(String model) {
          int row =   jdbc.sql("DELETE FROM tvs WHERE model = :model")
                    .param("model", model)
                    .update();

          if (row == 0 ) {
             throw  new ProductNotFoundException("model not available");
          }


    }


    private int countModel(String model) {
        RowCountCallbackHandler countCallbackHandler = new RowCountCallbackHandler();
        jdbc.sql("SELECT model FROM tvs WHERE model =:model")
                .param("model", model)
                .query(countCallbackHandler);
        return countCallbackHandler.getRowCount();

    }


}
