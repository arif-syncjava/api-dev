package com.arifsyncjava.apidev.pendrive.service;

import com.arifsyncjava.apidev.Command;
import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import com.arifsyncjava.apidev.pendrive.repository.PendriveRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.AlternativeJdkIdGenerator;

@Service
public class CreatePenDriveService  implements Command<PenDrive,PenDrive> {

    private final PendriveRepository pendriveRepository;


    public CreatePenDriveService(PendriveRepository pendriveRepository) {
        this.pendriveRepository = pendriveRepository;
    }


    @Override
    public ResponseEntity<PenDrive> execute(PenDrive penDrive) {
        String id = new AlternativeJdkIdGenerator().generateId().toString();
        penDrive.setId(id);
        PenDrive savedPenDrive = pendriveRepository.save(penDrive);
        return ResponseEntity.ok(savedPenDrive);
    }
}
