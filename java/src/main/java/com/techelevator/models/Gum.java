package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends Items{
    public Gum(String location, String name, BigDecimal price, String itemType) {
        super(location, name, price, itemType);
    }

    @Override
    public String displayMessage() {
        return "\"Chew Chew, Yum!\"";
    }
}
