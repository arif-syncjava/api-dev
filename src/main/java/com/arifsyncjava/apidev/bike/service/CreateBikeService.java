package com.arifsyncjava.apidev.bike.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.dto.BikeDTO;
import com.arifsyncjava.apidev.bike.repository.BikeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateBikeService implements Command<Bike, BikeDTO> {

    private final BikeRepository bikeRepository;

    public CreateBikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }


    @Override
    public ResponseEntity<BikeDTO> execute( Bike request) {
        Bike savedBike = bikeRepository.save(request);
        return ResponseEntity.ok(new BikeDTO(savedBike));
    }


}
