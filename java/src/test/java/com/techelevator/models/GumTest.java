package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void displayMessage_returns_Chew_Chew_Yum() {
        Gum gum = new Gum("D1","U-Chews", BigDecimal.valueOf(0.85),"Gum");
        String actual = gum.displayMessage();
        String expected = "\"Chew Chew, Yum!\"";
    }
}