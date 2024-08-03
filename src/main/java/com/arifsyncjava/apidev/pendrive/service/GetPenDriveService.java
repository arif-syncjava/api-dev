package com.arifsyncjava.apidev.pendrive.service;

import com.arifsyncjava.apidev.Query;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import com.arifsyncjava.apidev.pendrive.repository.PendriveRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetPenDriveService implements Query<String, PenDrive> {

    private final PendriveRepository pendriveRepository;

    public GetPenDriveService(PendriveRepository pendriveRepository) {
        this.pendriveRepository = pendriveRepository;
    }


    @Override
    public ResponseEntity<PenDrive> execute(String id) {
        PenDrive penDrive = pendriveRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("pendrive not found"));
        return ResponseEntity.ok(penDrive);
    }
}
