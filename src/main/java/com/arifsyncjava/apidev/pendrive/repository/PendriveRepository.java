package com.arifsyncjava.apidev.pendrive.repository;

import com.arifsyncjava.apidev.pendrive.model.PenDrive;

import java.util.Optional;

public interface PendriveRepository {

    Optional<PenDrive> findById (String id);
    PenDrive save (PenDrive penDrive);
    PenDrive update (PenDrive penDrive);
    void delete (String id);




}
