package com.arifsyncjava.apidev.earphone.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.earphone.model.Earphone;
import com.arifsyncjava.apidev.earphone.model.Mapping;
import com.arifsyncjava.apidev.earphone.repository.EarphoneRepository;
import com.arifsyncjava.apidev.earphone.request.CreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateEarphoneService implements Command<CreateRequest, Earphone> {

    private final EarphoneRepository earphoneRepository;
    private final Mapping mapping;

    public CreateEarphoneService(EarphoneRepository earphoneRepository, Mapping mapping) {
        this.earphoneRepository = earphoneRepository;
        this.mapping = mapping;
    }

    @Override
    public ResponseEntity<Earphone> execute(CreateRequest request) {
        Earphone earphone = mapping.mapToModel(request);
        Earphone savedEarphone = earphoneRepository.save(earphone);
        return ResponseEntity.ok(savedEarphone);
    }



}
