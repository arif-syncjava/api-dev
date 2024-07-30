package com.arifsyncjava.apidev.television;

import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.repository.TVRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@JdbcTest @ComponentScan
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TVRepositoryTest {

    @Autowired
    private TVRepositoryImpl tvRepository;

    @Test
    public void getTelevisionByModel_returnsSuccess() {
        Optional<Television> optional = tvRepository.getTelevisionByModel("225");
        Assertions.assertEquals("225", optional.get().getModel());
    }

    @Test
    public void getTelevisionByModel_returnsNull() {
        Optional<Television> optional = tvRepository.getTelevisionByModel("random");
        Assertions.assertEquals(Optional.empty(), optional);
    }



}
