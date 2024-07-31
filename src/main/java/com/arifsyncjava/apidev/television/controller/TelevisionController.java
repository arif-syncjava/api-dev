package com.arifsyncjava.apidev.television.controller;

import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.request.CreateRequest;
import com.arifsyncjava.apidev.television.service.CreateTVService;
import com.arifsyncjava.apidev.television.service.GetTVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tv")
public class TelevisionController {

    private final GetTVService getTVService;
    private final CreateTVService createTVService;

    public TelevisionController(GetTVService getTVService, CreateTVService createTVService) {
        this.getTVService = getTVService;
        this.createTVService = createTVService;
    }


    @GetMapping (path = "/{model}")
    public ResponseEntity<HttpResponse> getTelevisionByModel (@PathVariable("model") String model) {
        return getTVService.execute(model);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createTelevision (@RequestBody CreateRequest request) {
        return createTVService.execute(request);
    }







}
