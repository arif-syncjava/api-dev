package com.arifsyncjava.apidev.earphone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "earphones")
public class Earphone {
    @Id
    private Long id;
    private String productId;
    private String model;
    private String price;

    public Long getKey() {
        return id;
    }

    public void setKey(Long key) {
        this.id = key;
    }

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
