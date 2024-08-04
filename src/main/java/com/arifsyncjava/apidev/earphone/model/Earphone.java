package com.arifsyncjava.apidev.earphone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "earphones")
public class Earphone {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
