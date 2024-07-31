package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.exceptions.InvalidException;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.model.CreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ValidatorServiceTest {



    private ValidatorService validatorService;

    @BeforeEach
    void setup() {
        validatorService = new ValidatorService();
    }



    @Test
    public void  validatorService_returnsSuccess() {
        CreateRequest request = new CreateRequest();
        request.setBrand("a");
        request.setModel("xyz");
        request.setSize("30cm");
        request.setPrice("250tk");

        Television validTv = validatorService.execute(request);

        Assertions.assertEquals("a", validTv.getBrand());



    }

    @Test
    public void  validatorService_returnsException() {
        CreateRequest request = new CreateRequest();
        request.setBrand("a");
        request.setModel("  ");
        request.setSize("30cm");
        request.setPrice("250tk");

        InvalidException exception = Assertions.assertThrows(InvalidException.class,
                ()->validatorService.execute(request));

        Assertions.assertEquals("valid model required", exception.getMessage());



    }







}
