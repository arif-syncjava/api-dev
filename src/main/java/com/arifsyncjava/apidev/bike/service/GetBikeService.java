package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.Query;
import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.model.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GetBikeService implements Query<String,BikeDTO> {

    private final BikeRepository bikeRepository;

    public GetBikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }


    @Override
    public ResponseEntity<BikeDTO> execute(String model) {
     Bike bike = bikeRepository.findByBikeModel(model)
                .orElseThrow(() -> new ProductNotFoundException("bike model not found"));
        return ResponseEntity.ok(new BikeDTO(bike));
    }
}
