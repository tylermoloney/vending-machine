package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Items{

    public Candy(String location, String name, BigDecimal price, String itemType) {
        super(location, name, price, itemType);
    }


    @Override
    public String displayMessage() {
        return "\"Munch Munch, Yum!\"";
    }


}
