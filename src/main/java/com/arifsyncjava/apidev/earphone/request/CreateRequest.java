package com.arifsyncjava.apidev.earphone.request;

import jakarta.validation.constraints.NotBlank;

public class CreateRequest {
    @NotBlank (message = "model required")
    private String model;
    @NotBlank (message = "price required")
    private String price;



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
