package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Items {

    private String name;
    private String location;
    private BigDecimal price;
    private String itemType;
    private int quantity;  //we think this should go here...let's see if it works


    public Items(String location, String name, BigDecimal price, String itemType) {
        this.location = location;
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.quantity = 5;
    }
    public abstract String displayMessage();

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
