package com.techelevator;

import com.techelevator.application.VendingMachine;

import java.io.IOException;

public class VendingMachineCLI 
{
    public static void main(String[] args) throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.run();
    }
    
}
