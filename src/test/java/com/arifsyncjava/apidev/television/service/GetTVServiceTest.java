package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.exceptions.ProductNotFoundException;
import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.repository.TVRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTVServiceTest {

    @Mock
    private TVRepository tvRepository;

    @InjectMocks
    private GetTVService getTVService;

    @Test
    public void getTVService_returnsSuccess(){
        Television tv = new Television();
        String model = "b";
        tv.setModel(model);
        when(tvRepository.getTelevisionByModel(model)).thenReturn(Optional.of(tv));

        ResponseEntity<HttpResponse> response = getTVService.execute(model);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getTVService_returnsException(){
        Television tv = new Television();
        String model = "b";
        tv.setModel(model);
        when(tvRepository.getTelevisionByModel("nonExist")).thenReturn(Optional.empty());

       ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
               ()->getTVService.execute("nonExist"));

        Assertions.assertEquals("model not available", exception.getMessage());


    }









}
