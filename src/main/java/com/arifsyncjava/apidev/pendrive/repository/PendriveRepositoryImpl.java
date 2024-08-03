package com.arifsyncjava.apidev.pendrive.repository;

import com.arifsyncjava.apidev.exceptions.ApiException;
import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class PendriveRepositoryImpl implements PendriveRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PendriveJpaRepository pendriveJpaRepository;

    public PendriveRepositoryImpl (
          @Qualifier ("bikeJdbcTemplate")  NamedParameterJdbcTemplate jdbcTemplate,
          PendriveJpaRepository pendriveJpaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pendriveJpaRepository = pendriveJpaRepository;
    }

    private final String INSERT_QUERY = " INSERT INTO" +
            " pendrives (id,brand,storage,price) VALUES (:id,:brand,:storage,:price) ";
    private final String DELETE_QUERY = "DELETE FROM pendrives WHERE id = :id " ;


    @Override
    public Optional<PenDrive> findById(String id) {
        return pendriveJpaRepository.findById(id);
    }

    @Override
    public PenDrive save(PenDrive penDrive) {
        try {
            SqlParameterSource parameters = getSqlParameterSource(penDrive);
            jdbcTemplate.update(INSERT_QUERY, parameters);
            return pendriveJpaRepository.findById(penDrive.getId()).get();
        } catch (DataAccessException exception) {
            throw new ApiException(exception.getMessage());
        }

    }

    @Override
    public PenDrive update(PenDrive penDrive) {
        return pendriveJpaRepository.save(penDrive);
    }


    @Override
    public void delete(String id) {
        jdbcTemplate
                .update(DELETE_QUERY,
                        Map.of("id", id));
    }


    private SqlParameterSource getSqlParameterSource(PenDrive penDrive) {
        return new MapSqlParameterSource()
                .addValue("id", penDrive.getId())
                .addValue("brand", penDrive.getBrand())
                .addValue("storage", penDrive.getStorage())
                .addValue("price", penDrive.getPrice());

    }


}
