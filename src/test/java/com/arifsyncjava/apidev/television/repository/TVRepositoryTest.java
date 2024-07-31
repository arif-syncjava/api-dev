package com.arifsyncjava.apidev.television.repository;

import com.arifsyncjava.apidev.exceptions.ApiException;
import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.television.model.Television;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@JdbcTest @ComponentScan
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TVRepositoryTest {

    @Autowired
    private TVRepository tvRepository;

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


    //////////////////////// Create Operation Test //////////////////////////

    @Test
    public void  saveTelevision_returnsSuccess() {
        Television television = new Television();
        television.setBrand("a");
        television.setModel("b");
        television.setSize("20cm");
        television.setPrice("4tk");

        Television savedTv = tvRepository.saveTelevision(television);

        Assertions.assertEquals("b", television.getModel());
    }

    @Test
    public void  saveTelevision_returnsException() {
        Television television = new Television();
        television.setBrand("a");
        television.setModel("b");
        television.setSize("20cm");
        television.setPrice(null);

        ApiException exception = assertThrows(ApiException.class,
                ()-> tvRepository.saveTelevision(television));

        Assertions.assertEquals("An error occurred. Please try again.",
                exception.getMessage());

    }


    ////////////////// delete operation  //////////////////
    @Test
    public void deleteTelevision_returnsSuccess () {
        tvRepository.deleteTelevision("df");

    }

    @Test
    public void deleteTelevision_returnsException() {

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                ()-> tvRepository.deleteTelevision("nonExistModel"));

        Assertions.assertEquals("model not available",
                exception.getMessage());
    }






















}
