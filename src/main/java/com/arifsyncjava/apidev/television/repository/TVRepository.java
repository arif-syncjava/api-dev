package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.television.model.Television;

import java.util.Optional;
import java.util.function.IntPredicate;

public interface TVRepository {

    Optional<Television> getTelevisionByModel (String model);
    Television saveTelevision (Television television);
    Television updateTelevision (Television television);
     void deleteTelevision (String model);

}
