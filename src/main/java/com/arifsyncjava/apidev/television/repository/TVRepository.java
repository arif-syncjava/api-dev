package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.television.model.Television;

import java.util.Optional;

public interface TVRepository {

    Optional<Television> getTelevisionByModel (String model);
}
