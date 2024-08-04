package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.repository.TVRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeleteTVService implements Command<String, HttpResponse> {

    private final TVRepository tvRepository;

    public DeleteTVService(TVRepository tvRepository) {
        this.tvRepository = tvRepository;
    }


    @Override
    public ResponseEntity<HttpResponse> execute(String model) {
     Television tv = tvRepository.getTelevisionByModel(model)
              .orElseThrow(()->new ProductNotFoundException("model not available"));
       tvRepository.deleteTelevision(model);
        return ResponseEntity.ok(new HttpResponse(HttpStatus.OK,
                Map.of("deleted", "true")));
    }

}
