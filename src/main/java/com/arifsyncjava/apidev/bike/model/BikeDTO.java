package com.arifsyncjava.apidev.bike.model;

public class BikeDTO {
    private String brand;
    private String bikeModel;
    private String color;
    private String price;

    public BikeDTO (Bike bike) {
        this.brand   =bike.getBrand();
        this.bikeModel = bike.getBikeModel();
        this.color = bike.getColor();
        this.price = bike.getPrice();
    }

    public String getBrand() {
        return brand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }
}
