package com.swd392.backpackstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Backpack")
public class Backpack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int quantity;
    private double price;
    private int selled;

    public Backpack() {
    }

    public Backpack(int id, String type, int quantity, double price, int selled) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.selled = selled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSelled() {
        return selled;
    }

    public void setSelled(int selled) {
        this.selled = selled;
    }
}
