package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.model.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateBikeService implements Command<Bike, BikeDTO> {

    private final BikeRepository bikeRepository;

    public UpdateBikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }


    @Override
    public ResponseEntity<BikeDTO> execute(Bike request) {
        Bike savedBike = bikeRepository
                .findByBikeModel(request.getBikeModel())
                .orElseThrow(() -> new ProductNotFoundException("bike model not available"));

        savedBike.setBikeModel(request.getBikeModel());
        savedBike.setBrand(request.getBrand());
        savedBike.setColor(request.getColor());
        savedBike.setPrice(request.getPrice());

        Bike updatedBike = bikeRepository.save(savedBike);
        return ResponseEntity.ok(new BikeDTO(updatedBike));


    }


}
