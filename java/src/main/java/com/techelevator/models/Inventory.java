package com.techelevator.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.techelevator.models.FileReader;
import com.techelevator.ui.UserOutput;

public class Inventory {
    //instantiate the list
    List<Items> inventoryList = new ArrayList<>();

    //add items to the list
    public void addInventory(Items item) {
        inventoryList.add(item);
    }

    //constructors - one default, one accepts List
    public Inventory() {
    }

    public Inventory(List<Items> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<Items> getInventoryList() {
        return inventoryList;
    }

    //prints out contents of the list
    public String printList() {
        String location = "";
        String name = "";
        BigDecimal price = BigDecimal.valueOf((0.00));
        String itemType = "";
        int quantity = 0;
        String stringQuantity = "";
        String stringToReturn = "";
        for (int i = 0; i < inventoryList.size(); i++) {
            location = inventoryList.get(i).getLocation();
            name = inventoryList.get(i).getName();
            price = inventoryList.get(i).getPrice();
            itemType = inventoryList.get(i).getItemType();
            quantity = inventoryList.get(i).getQuantity();
            if (quantity == 0){
                stringQuantity = "SOLD OUT";
            }
            else {
                stringQuantity = String.valueOf(quantity);
            }
            stringToReturn += (location + " " + name + " $" + price + " " + itemType + " " + stringQuantity + "\n");
        }
        return stringToReturn;
    }
}


