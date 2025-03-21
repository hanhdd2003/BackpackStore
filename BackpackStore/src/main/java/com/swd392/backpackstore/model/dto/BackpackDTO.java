package com.swd392.backpackstore.model.dto;

public class BackpackDTO {
    private String type;
    private int quantity;
    private double price;
    private int selled;
    private String description;
    public BackpackDTO() {}

    public BackpackDTO(String type, int quantity, double price, int selled, String description) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.selled = selled;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
