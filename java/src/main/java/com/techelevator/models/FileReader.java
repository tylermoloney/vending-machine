package com.techelevator.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import com.techelevator.models.Inventory;

public class FileReader {

    public List<Items> readFile() {
        //Create an inventory object
        Inventory readFileInventory = new Inventory();

        //read in file
        File fileName = new File("vendingmachine.csv");
        Scanner fileReader;
        {
            try {
                fileReader = new Scanner(fileName);
                //iterate through file
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    //splits at each pipe delimiter
                    String[] lineSplit = line.split("\\|");
                    //convert price to BigDecimal from String to properly feed into Items constructor
                    BigDecimal price = new BigDecimal(lineSplit[2]);
                    //Switch based on the last entry in the array (Chip, Candy, etc)
                    switch (lineSplit[lineSplit.length - 1]) {
                        case "Candy":
                            Candy candy = new Candy(lineSplit[0], lineSplit[1], price, lineSplit[3]);
                            readFileInventory.addInventory(candy);
                            break;
                        case "Chip":
                            Chips chip = new Chips(lineSplit[0], lineSplit[1], price, lineSplit[3]);
                            readFileInventory.addInventory(chip);
                            break;
                        case "Drink":
                            Drink drink = new Drink(lineSplit[0], lineSplit[1], price, lineSplit[3]);
                            readFileInventory.addInventory(drink);
                            break;
                        case "Gum":
                            Gum gum = new Gum(lineSplit[0], lineSplit[1], price, lineSplit[3]);
                            readFileInventory.addInventory(gum);
                            break;

                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        //give the List we've created back to the method that called it in VendingMachine
        return readFileInventory.inventoryList;
    }
}