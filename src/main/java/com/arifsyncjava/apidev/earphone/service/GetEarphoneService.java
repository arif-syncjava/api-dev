package com.arifsyncjava.apidev.earphone.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.earphone.model.Earphone;
import com.arifsyncjava.apidev.earphone.repository.EarphoneRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEarphoneService implements Command<Long, Earphone> {

    private final EarphoneRepository earphoneRepository;

    public GetEarphoneService(EarphoneRepository earphoneRepository) {
        this.earphoneRepository = earphoneRepository;
    }


    @Override
    public ResponseEntity<Earphone> execute(Long id) {
        Earphone earphone = earphoneRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("model not available"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(earphone);
    }
}
