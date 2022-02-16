package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Items{

    public Drink( String location, String name, BigDecimal price, String itemType) {
        super(location, name, price, itemType);
    }

    @Override
    public String displayMessage() {
        return "\"Glug Glug, Yum!\"";
    }
}
