package com.arifsyncjava.apidev.bike.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "bikes")
public class Bike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "brand required")
    private String brand;
    @NotBlank(message = "bikemodel required")
    private String bikeModel;
    @NotBlank(message = "color required")
    private String color;
    @NotBlank(message = "price required")
    private String price;

    public Integer getId() {
        return id;
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
