package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.model.TelevisionDTO;
import com.arifsyncjava.apidev.television.repository.TVRepository;
import com.arifsyncjava.apidev.television.model.CreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreateTVService implements Command<CreateRequest, HttpResponse> {

    private final TVRepository tvRepository;
    private final ValidatorService validatorService;

    public CreateTVService(TVRepository tvRepository, ValidatorService validatorService) {
        this.tvRepository = tvRepository;
        this.validatorService = validatorService;
    }


    @Override
    public ResponseEntity<HttpResponse> execute(CreateRequest request) {
        Television validRequest = validatorService.execute(request);
        Television television = tvRepository.saveTelevision(validRequest);
        return ResponseEntity.ok(
                new HttpResponse(HttpStatus.OK,
                        Map.of("tv",new TelevisionDTO(television))));
    }
}
