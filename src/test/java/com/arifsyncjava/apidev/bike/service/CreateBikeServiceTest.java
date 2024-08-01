package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.dto.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateBikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private CreateBikeService createBikeService;

    @Test
    public void createBikeService_returnsSuccess() {

        Bike bike = new Bike();
        bike.setBrand("abc");
        bike.setBikeModel("xyz");
        bike.setColor("red");
        bike.setPrice("30K");

        when(bikeRepository.save(any(Bike.class))).thenReturn(bike);

        ResponseEntity<BikeDTO> response = createBikeService.execute(bike);

        assertEquals("abc",response.getBody().getBrand());

    }

























}
