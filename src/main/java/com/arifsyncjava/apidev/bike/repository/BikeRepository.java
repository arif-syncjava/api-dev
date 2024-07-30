package com.arifsyncjava.apidev.bike.repository;

import com.arifsyncjava.apidev.bike.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BikeRepository extends JpaRepository<Bike,Integer> {

    Optional<Bike> findByBikeModel (String model);






}
