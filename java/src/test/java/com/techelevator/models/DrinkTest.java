package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void displayMessage_returns_Glug_Glug_Yum() {
        Drink drink = new Drink("C2","Dr. Salt", BigDecimal.valueOf(1.50),"Drink");
        String actual = drink.displayMessage();
        String expected = "\"Glug Glug, Yum!\"";
    }
}