package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.television.model.HttpResponse;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.repository.TVRepository;
import com.arifsyncjava.apidev.television.model.CreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTVServiceTest {

    @Mock
    private TVRepository tvRepository;
    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private CreateTVService createTVService;

    @Test
    public void createTVService_returnSuccess() {
        CreateRequest request = new CreateRequest();
        request.setBrand("a");
        request.setModel("xyz");
        request.setSize("30cm");
        request.setPrice("250tk");

        Television tv = new Television();


        when(validatorService.execute(request)).thenReturn(tv);
        when(tvRepository.saveTelevision(tv)).thenReturn(tv);

        ResponseEntity<HttpResponse> response = createTVService.execute(request);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

    }







}
