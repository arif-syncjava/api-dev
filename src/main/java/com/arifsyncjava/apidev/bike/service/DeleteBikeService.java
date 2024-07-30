package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteBikeService implements Command<String,Void> {

    private final BikeRepository bikeRepository;

    public DeleteBikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String model) {
        Bike bike = bikeRepository.findByBikeModel(model)
                .orElseThrow(() -> new ProductNotFoundException("bike model not available"));
        bikeRepository.delete(bike);
        return ResponseEntity.ok().build();
    }
}
