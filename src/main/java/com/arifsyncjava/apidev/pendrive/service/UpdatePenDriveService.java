package com.arifsyncjava.apidev.pendrive.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import com.arifsyncjava.apidev.pendrive.repository.PendriveRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdatePenDriveService implements Command<PenDrive, PenDrive> {

    private final PendriveRepository pendriveRepository;

    public UpdatePenDriveService(PendriveRepository pendriveRepository) {
        this.pendriveRepository = pendriveRepository;
    }


    @Override
    public ResponseEntity<PenDrive> execute(PenDrive penDrive) {
        String id = penDrive.getId();
        PenDrive savedPenDrive = pendriveRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("pendrive not found"));

        savedPenDrive.setId(penDrive.getId());
        savedPenDrive.setBrand(penDrive.getBrand());
        savedPenDrive.setStorage(penDrive.getStorage());
        savedPenDrive.setPrice(penDrive.getPrice());

        PenDrive updatedPenDrive = pendriveRepository.update(penDrive);

        return ResponseEntity.ok(updatedPenDrive);
    }
}
