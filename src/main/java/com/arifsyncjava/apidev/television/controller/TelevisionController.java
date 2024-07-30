package com.arifsyncjava.apidev.television.controller;

import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.service.GetTVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tv")
public class TelevisionController {

    private final GetTVService getTVService;

    public TelevisionController(GetTVService getTVService) {
        this.getTVService = getTVService;
    }

    @GetMapping (path = "/{model}")
    public ResponseEntity<HttpResponse> getTelevisionByModel (@PathVariable("model") String model) {
        return getTVService.execute(model);
    }


}
