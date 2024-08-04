package com.arifsyncjava.apidev.television.controller;

import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.model.CreateRequest;
import com.arifsyncjava.apidev.television.service.CreateTVService;
import com.arifsyncjava.apidev.television.service.DeleteTVService;
import com.arifsyncjava.apidev.television.service.GetTVService;
import com.arifsyncjava.apidev.television.service.UpdateTVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tvs")
public class TelevisionController {

    private final GetTVService getTVService;
    private final CreateTVService createTVService;
    private final UpdateTVService updateTVService;
    private final DeleteTVService deleteTVService;

    public TelevisionController(GetTVService getTVService, CreateTVService createTVService, UpdateTVService updateTVService, DeleteTVService deleteTVService) {
        this.getTVService = getTVService;
        this.createTVService = createTVService;
        this.updateTVService = updateTVService;
        this.deleteTVService = deleteTVService;
    }


    @GetMapping (path = "/{model}")
    public ResponseEntity<HttpResponse> getTelevisionByModel (@PathVariable("model") String model) {
        return getTVService.execute(model);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createTelevision (@RequestBody CreateRequest request) {
        return createTVService.execute(request);
    }

    @PutMapping
    public ResponseEntity<HttpResponse> updateTelevision (@RequestBody CreateRequest request) {
        return updateTVService.execute(request);
    }

    @DeleteMapping (path = "/{model}")
    public ResponseEntity<HttpResponse> deleteTelevision (@PathVariable String model) {
        return deleteTVService.execute(model);
    }







}
