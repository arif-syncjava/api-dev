package com.arifsyncjava.apidev.earphone.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CreateRequest {
    @NotBlank (message = "unique product id required")
    private String productId;
    @NotBlank (message = "unique product id required")
    private String model;
    @NotBlank (message = "unique product id required")
    private String price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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
