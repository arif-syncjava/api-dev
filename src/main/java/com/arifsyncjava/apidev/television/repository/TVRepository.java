package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.request.CreateRequest;

import java.util.Optional;

public interface TVRepository {

    Optional<Television> getTelevisionByModel (String model);
    Television saveTelevision (Television television);

}
