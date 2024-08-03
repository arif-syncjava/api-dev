package com.arifsyncjava.apidev.earphone.repository;

import com.arifsyncjava.apidev.earphone.model.Earphone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EarphoneRepository extends JpaRepository<Earphone,Long> {


    Optional<Earphone> findByProductId(String id);


}
