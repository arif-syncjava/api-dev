package com.arifsyncjava.apidev.pendrive.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import com.arifsyncjava.apidev.pendrive.repository.PendriveRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePenDriveService implements Command<String,Void> {

    private final PendriveRepository pendriveRepository;

    public DeletePenDriveService(PendriveRepository pendriveRepository) {
        this.pendriveRepository = pendriveRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        PenDrive savedPenDrive = pendriveRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("pendrive not found"));

        pendriveRepository.delete(savedPenDrive.getId());

        return ResponseEntity.ok().build();
    }
}
