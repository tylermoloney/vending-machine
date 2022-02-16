package com.techelevator.models;

import java.math.BigDecimal;

public class Chips extends Items{


    public Chips(String location,String name,  BigDecimal price, String itemType) {
        super(location, name, price, itemType);
    }

    @Override
    public String displayMessage() {
        return "\"Crunch Crunch, Yum!\"";
    }
}
