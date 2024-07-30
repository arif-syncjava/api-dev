package com.arifsyncjava.apidev.bike.controller;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.model.BikeDTO;
import com.arifsyncjava.apidev.bike.service.CreateBikeService;
import com.arifsyncjava.apidev.bike.service.GetBikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping (path = "/bikes")
public class BikeController {

    private final GetBikeService getBikeService;
    private final CreateBikeService createBikeService;

    public BikeController(GetBikeService getBikeService, CreateBikeService createBikeService) {
        this.getBikeService = getBikeService;
        this.createBikeService = createBikeService;
    }


    @PostMapping
    public ResponseEntity<BikeDTO> createNewBike (@RequestBody Bike request) {
        return createBikeService.execute(request);

    }

    @GetMapping (path = "/{model}")
    public ResponseEntity<BikeDTO> getBikeByModel (@PathVariable("model") String model) {
        return getBikeService.execute(model);

    }





}
