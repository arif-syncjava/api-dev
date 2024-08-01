package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.dto.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private GetBikeService getBikeService;

    @Test
    public void getGetBikeService_returnsSuccess () {
        Bike bike = new Bike();
        bike.setBrand("pulsar");
        bike.setBikeModel("123abc");
        bike.setColor("red");
        bike.setPrice("25k");

        when(bikeRepository.findByBikeModel("123abc")).thenReturn(Optional.of(bike));

        ResponseEntity<BikeDTO> response = getBikeService.execute("123abc");

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void getGetBikeService_returnsException () {
        Bike bike = new Bike();
        String model = "123abc";
        bike.setBrand("pulsar");
        bike.setBikeModel(model);
        bike.setColor("red");
        bike.setPrice("25k");

        when(bikeRepository.findByBikeModel(model)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
        ()->getBikeService.execute(model));

        assertEquals("bike model not available", exception.getMessage());


    }












}
