package com.arifsyncjava.apidev.bike.controller;

import com.arifsyncjava.apidev.bike.model.Bike;
import com.arifsyncjava.apidev.bike.model.BikeDTO;
import com.arifsyncjava.apidev.bike.service.CreateBikeService;
import com.arifsyncjava.apidev.bike.service.DeleteBikeService;
import com.arifsyncjava.apidev.bike.service.GetBikeService;
import com.arifsyncjava.apidev.bike.service.UpdateBikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping (path = "/bikes")
public class BikeController {

    private final GetBikeService getBikeService;
    private final CreateBikeService createBikeService;
    private final UpdateBikeService updateBikeService;
    private final DeleteBikeService deleteBikeService;

    public BikeController(GetBikeService getBikeService, CreateBikeService createBikeService, UpdateBikeService updateBikeService, DeleteBikeService deleteBikeService) {
        this.getBikeService = getBikeService;
        this.createBikeService = createBikeService;
        this.updateBikeService = updateBikeService;
        this.deleteBikeService = deleteBikeService;
    }


    @PostMapping
    public ResponseEntity<BikeDTO> createNewBike (@RequestBody Bike request) {
        return createBikeService.execute(request);

    }

    @GetMapping (path = "/{model}")
    public ResponseEntity<BikeDTO> getBikeByModel (@PathVariable("model") String model) {
        return getBikeService.execute(model);
    }

    @PutMapping
    public ResponseEntity<BikeDTO> updateBike (@RequestBody Bike request) {
        return updateBikeService.execute(request);
    }

    @DeleteMapping(path = "/{model}")
    public void deleteBike (@PathVariable ("model") String model) {
        deleteBikeService.execute(model);
    }





}
