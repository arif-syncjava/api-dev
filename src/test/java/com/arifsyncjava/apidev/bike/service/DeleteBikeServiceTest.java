package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteBikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private DeleteBikeService deleteBikeService;

    @Test
    public void deleteBikeService_returnsSuccess() {
        Bike bike = new Bike();
        bike.setId(1);
        String model = "123abc";
        bike.setBrand("pulsar");
        bike.setBikeModel(model);
        bike.setColor("red");
        bike.setPrice("25k");

        when(bikeRepository.findByBikeModel(model)).thenReturn(Optional.of(bike));

        ResponseEntity<Void> response = deleteBikeService.execute(model);
        verify(bikeRepository).delete(bike);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void deleteBikeService_returnsException() {
        Bike bikeRequest = new Bike();
        bikeRequest.setBrand("abc");
        bikeRequest.setBikeModel("xyz");

        when(bikeRepository.findByBikeModel("nonExist")).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                ()->deleteBikeService.execute("nonExist"));

        assertEquals("bike model not available", exception.getMessage());


    }













}
