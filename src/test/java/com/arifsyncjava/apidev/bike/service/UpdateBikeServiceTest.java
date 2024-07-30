package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.model.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateBikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private UpdateBikeService updateBikeService;

    @Test
    public void updateBikeService_returnsSuccess() {
        Bike bikeRequest = new Bike();
        bikeRequest.setBrand("abc");
        bikeRequest.setBikeModel("xyz");

        when(bikeRepository.findByBikeModel("xyz")).thenReturn(Optional.of(bikeRequest));
        bikeRequest.setBrand("newBrand");
        when(bikeRepository.save(any(Bike.class))).thenReturn(bikeRequest);

        ResponseEntity<BikeDTO> response = updateBikeService.execute(bikeRequest);

        Assertions.assertEquals("newBrand", response.getBody().getBrand());


    }


    @Test
    public void updateBikeService_returnsException() {
        Bike bike = new Bike();
        String model = "123abc";
        bike.setBrand("pulsar");
        bike.setBikeModel(model);
        bike.setColor("red");
        bike.setPrice("25k");

        when(bikeRepository.findByBikeModel(model)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                ()->updateBikeService.execute(bike));

        assertEquals("bike model not available", exception.getMessage());


    }

















}
