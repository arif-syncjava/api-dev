package com.arifsyncjava.apidev.earphone.repository;

import com.arifsyncjava.apidev.earphone.model.Earphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarphoneRepository extends JpaRepository<Earphone,Long> {


}
