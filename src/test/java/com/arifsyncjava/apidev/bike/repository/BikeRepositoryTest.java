package com.arifsyncjava.apidev.bike.repository;

import com.arifsyncjava.apidev.bike.model.Bike;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BikeRepositoryTest {

    @Autowired
    private BikeRepository bikeRepository;


    @Test
   public void findByBikeModel_returnsSuccess() {
        Bike bike = new Bike();
        bike.setBrand("pulsar");
        bike.setBikeModel("123abc");
        bike.setColor("red");
        bike.setPrice("25k");
        bikeRepository.save(bike);

        Bike savedBike = bikeRepository.findByBikeModel("123abc").get();

        assertEquals(savedBike.getBikeModel(), bike.getBikeModel());


    }

    @Test
    public void findByBikeModel_returnsException() {
        Bike bike = new Bike();
        bike.setBrand("pulsar");
        bike.setBikeModel("123abc");
        bike.setColor("red");
        bike.setPrice("25k");
        bikeRepository.save(bike);

        Optional<Bike> optionalBike = bikeRepository.findByBikeModel("notExistModel");
        Assertions.assertTrue(optionalBike.isEmpty());






    }





}
