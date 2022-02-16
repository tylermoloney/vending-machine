package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void displayMessage_returns_crunch_crunch_yum() {
        Candy candy = new Candy("B1","Moonpie", BigDecimal.valueOf(1.80),"Candy");
        String actual = candy.displayMessage();
        String expected = "\"Munch Munch, Yum!\"";
    }
}