package com.arifsyncjava.apidev.television.model;

public class TelevisionDTO {
    private String brand;
    private String model;
    private String price;

    public TelevisionDTO (Television television) {
        this.brand = television.getBrand();
        this.model = television.getModel();
        this.price = television.getPrice();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }
}
