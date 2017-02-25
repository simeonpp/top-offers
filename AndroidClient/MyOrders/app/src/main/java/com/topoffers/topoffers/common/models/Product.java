package com.topoffers.topoffers.common.models;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private String imageIdentifier;
    private String description;

    public Product(int id, String title, double price, int quantity, String imageIdentifier, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imageIdentifier = imageIdentifier;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    public void setImageIdentifier(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
