package com.arifsyncjava.apidev.earphone.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.earphone.model.Earphone;
import com.arifsyncjava.apidev.earphone.repository.EarphoneRepository;
import com.arifsyncjava.apidev.earphone.request.UpdateRequest;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateEarphoneService implements Command<UpdateRequest, Earphone> {

    private final EarphoneRepository earphoneRepository;

    public UpdateEarphoneService(EarphoneRepository earphoneRepository) {
        this.earphoneRepository = earphoneRepository;
    }


    @Override
    public ResponseEntity<Earphone> execute(UpdateRequest request) {
        Earphone earphone = earphoneRepository.findById(request.getId())
                .orElseThrow(()->new ProductNotFoundException("product not available"));

        
        earphone.setModel(request.getRequestBody().getModel());
        earphone.setPrice(request.getRequestBody().getPrice());

        return ResponseEntity.ok(earphone);
    }



}
