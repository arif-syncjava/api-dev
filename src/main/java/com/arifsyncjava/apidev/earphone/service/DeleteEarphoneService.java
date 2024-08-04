package com.arifsyncjava.apidev.earphone.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.earphone.model.Earphone;
import com.arifsyncjava.apidev.earphone.repository.EarphoneRepository;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEarphoneService implements Command<Long,Void> {

    private final EarphoneRepository earphoneRepository;

    public DeleteEarphoneService(EarphoneRepository earphoneRepository) {
        this.earphoneRepository = earphoneRepository;
    }


    @Override
    public ResponseEntity<Void> execute(Long id) {
        Earphone earphone = earphoneRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not available"));
        earphoneRepository.delete(earphone);
        return ResponseEntity.ok().build();
    }
}
