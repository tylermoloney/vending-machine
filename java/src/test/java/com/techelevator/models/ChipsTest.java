package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipsTest {

    @Test
    public void displayMessage_returns_Crunch_Crunch_Yum() {
        Chips chips = new Chips("A1","Potato Crisps", BigDecimal.valueOf(3.05),"Chip");
        String actual = chips.displayMessage();
        String expected = "\"Crunch Crunch, Yum!\"";
    }
}