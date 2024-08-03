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
    private final String id;

    public CreatePenDriveService(PendriveRepository pendriveRepository, String id) {
        this.pendriveRepository = pendriveRepository;
        this.id = new AlternativeJdkIdGenerator().generateId().toString();
    }


    @Override
    public ResponseEntity<PenDrive> execute(PenDrive penDrive) {
        penDrive.setId(id);
        PenDrive savedPenDrive = pendriveRepository.save(penDrive);
        return ResponseEntity.ok(savedPenDrive);
    }
}
