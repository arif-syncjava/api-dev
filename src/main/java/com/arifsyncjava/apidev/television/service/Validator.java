package com.arifsyncjava.apidev.television.service;

import com.arifsyncjava.apidev.exceptions.InvalidException;
import com.arifsyncjava.apidev.television.model.Television;
import com.arifsyncjava.apidev.television.request.CreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Validator {

    public static Television execute(CreateRequest request) {

        if (!StringUtils.hasText(request.getBrand())) {
            throw new InvalidException("valid brand required");
        }
        if (!StringUtils.hasText(request.getModel())) {
            throw new InvalidException("valid model required");
        }
        if (!StringUtils.hasText(request.getPrice())) {
            throw new InvalidException("valid price required");
        }
        if (!StringUtils.hasText(request.getSize())) {
            throw new InvalidException("valid size required");
        }

        return new Television(request);


    }

}
