package com.arifsyncjava.apidev.pendrive.controller;

import com.arifsyncjava.apidev.pendrive.model.PenDrive;
import com.arifsyncjava.apidev.pendrive.service.CreatePenDriveService;
import com.arifsyncjava.apidev.pendrive.service.DeletePenDriveService;
import com.arifsyncjava.apidev.pendrive.service.GetPenDriveService;
import com.arifsyncjava.apidev.pendrive.service.UpdatePenDriveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "/pendrives")
public class PenDriveController {

    private final GetPenDriveService getPenDriveService;
    private final CreatePenDriveService createPenDriveService;
    private final UpdatePenDriveService updatePenDriveService;
    private final DeletePenDriveService deletePenDriveService;

    public PenDriveController(GetPenDriveService getPenDriveService, CreatePenDriveService createPenDriveService, UpdatePenDriveService updatePenDriveService, DeletePenDriveService deletePenDriveService) {
        this.getPenDriveService = getPenDriveService;
        this.createPenDriveService = createPenDriveService;
        this.updatePenDriveService = updatePenDriveService;
        this.deletePenDriveService = deletePenDriveService;
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<PenDrive> read (@PathVariable String id) {
        return getPenDriveService.execute(id);
    }

    @PostMapping
    public ResponseEntity<PenDrive> create ( @RequestBody PenDrive penDrive) {
        return createPenDriveService.execute(penDrive);
    }

    @PutMapping
    public ResponseEntity<PenDrive> update (@RequestBody PenDrive penDrive) {
        return updatePenDriveService.execute(penDrive);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable String id) {
        return deletePenDriveService.execute(id);
    }






}
