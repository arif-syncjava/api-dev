package com.arifsyncjava.apidev.earphone.model;

import com.arifsyncjava.apidev.earphone.request.CreateRequest;
import org.springframework.stereotype.Component;

@Component
public class Mapping {

    public static Earphone mapToModel  (CreateRequest request) {
        Earphone earphone = new Earphone();
        earphone.setModel(request.getModel());
        earphone.setPrice(request.getPrice());
        return earphone;
    }



}
