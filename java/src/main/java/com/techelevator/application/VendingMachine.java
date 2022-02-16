package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    Money money = new Money();
    FileReader fileReader = new FileReader();
    Inventory inventory = new Inventory(fileReader.readFile());
    Logger logger = new Logger();
    SalesReport salesReport;

    {
        salesReport = new SalesReport(inventory.getInventoryList());
    }
    Map<String,Integer> newSalesReport = salesReport.createSalesMap();

    public VendingMachine(){
    }


    public void run() throws IOException {
        while (true) {

            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                // display the vending machine slots
                UserOutput.displayMessage(inventory.printList());

            } else if (choice.equals("purchase")) {

                Boolean makingPurchase = true;
                while (makingPurchase) {
                    UserOutput.displayPurchaseScreen();

                    String purchaseChoice = UserInput.getPurchaseOption(money);
                    if (purchaseChoice.equals("Feed Money")) {
                        //Receive choice from user
                        String option = UserInput.receiveMoney();
                        receiveMoney(option);

                    } else if (purchaseChoice.equals("Select Product")) {
                        UserOutput.displayMessage(inventory.printList());
                        String option = UserInput.makePurchase(inventory.getInventoryList());
                        makePurchase(inventory.getInventoryList(), option);


                    } else if (purchaseChoice.equals("Finish Transaction")) {
                        dispenseChange(money);
                        makingPurchase = false;

                    } else {
                        UserOutput.displayMessage("Invalid selection");
                    }
                }

            }else if (choice.equals("sales report")){

                salesReport.writeSalesReportFile(newSalesReport);
                salesReport.close();
            }

            else if (choice.equals("exit")) {
                UserOutput.displayMessage("Good Bye!");
                logger.write("\\`\\`\\`");
                logger.close();
                break;
            }
        }
    }

    public BigDecimal receiveMoney(String option) {
        BigDecimal beginningBalance = money.getBalance();
        switch (option) {
            case "1":
                money.addMoney(new BigDecimal(1.00));
                logger.write("FEED MONEY: \\$" + beginningBalance + " \\$" + money.getBalance());
                return money.getBalance();
            case "2":
                money.addMoney(new BigDecimal(2.00));
                logger.write("FEED MONEY: \\$" + beginningBalance + " \\$" + money.getBalance());
                return money.getBalance();
            case "5":
                money.addMoney(new BigDecimal(5.00));
                logger.write("FEED MONEY: \\$" + beginningBalance + " \\$" + money.getBalance());
                return money.getBalance();
            case "10":
                money.addMoney(new BigDecimal(10.00));
                logger.write("FEED MONEY: \\$" + beginningBalance + " \\$" + money.getBalance());
                return money.getBalance();
        }
        return money.getBalance();
    }

    public String makePurchase(List<Items> items, String option) {
        BigDecimal beginningBalance = money.getBalance();
        if (option.equals("x")) {
            UserOutput.displayMessage("Invalid Entry!");
            return "Invalid Entry!";
        }
        int i = Integer.parseInt(option);
        if (items.get(i).getQuantity() == 0) {
            UserOutput.displayMessage("Item is SOLD OUT");
            return "Sold Out";
        }
        if (money.getBalance().compareTo(items.get(i).getPrice()) < 0) {
            UserOutput.displayMessage("Insufficient Funds!");
            return "Insufficient Funds!";
        } else if (money.getBalance().compareTo(items.get(i).getPrice()) >= 0) {
            money.makePurchase(items.get(i).getPrice());
            items.get(i).setQuantity(items.get(i).getQuantity() - 1);
            //line to update sales in sales report map
            salesReport.updateSalesMap(items.get(i).getName(), newSalesReport);
            UserOutput.displayMessage("You purchased " + items.get(i).getName() + " at price $"
                    + items.get(i).getPrice() + "\nYour current balance is: $" + money.getBalance()
                    + "\n" + items.get(i).displayMessage());
            logger.write(items.get(i).getName() + " " + items.get(i).getLocation() + " \\$" + beginningBalance
                    + " \\$" + money.getBalance());
            return items.get(i).getName();
        }
        return "";
    }

    public String dispenseChange(Money money) {
        logger.write("GIVE CHANGE: \\$" + money.getBalance() + " \\$0.00");
        BigDecimal change = money.getBalance();
        BigDecimal[] result = change.divideAndRemainder(new BigDecimal(0.25));
        BigDecimal quarters = (result[0]);
        change = result[1].setScale(2, RoundingMode.HALF_UP);
        if (change.compareTo(new BigDecimal(0.00)) == 0) {
            String quarterString = "";
            if (quarters.compareTo(new BigDecimal(1)) == 0) {
                quarterString = "quarter";
            } else {
                quarterString = "quarters";
            }
            UserOutput.displayMessage("Your change is " + quarters + " " + quarterString);
            money.setBalance(new BigDecimal(0.00));
            return ("Your change is " + quarters + " " + quarterString);
        }
        result = change.divideAndRemainder(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
        BigDecimal dimes = result[0];
        change = result[1].setScale(2, RoundingMode.HALF_UP);
        if (change.compareTo(new BigDecimal(0.00)) == 0) {
            //handling dime vs dimes for 1 or 2 dimes
            String dimeString = "";
            if (dimes.compareTo(new BigDecimal(1)) == 1) {
                dimeString = "dimes";
            } else {
                dimeString = "dime";
            }
            UserOutput.displayMessage("Your change is " + quarters + " quarters and "
                    + dimes + " " + dimeString + ".");
            money.setBalance(new BigDecimal(0.00));
            return ("Your change is " + quarters + " quarters and " + dimes + " " + dimeString + ".");
        }
        //if we reach this point, there is still change to give. If we need to give nickels, we will only ever need
        //to give one, as we would've already given as many dimes as we can.
        String dimeString = "";
        if (dimes.compareTo(new BigDecimal(1)) == 1 || dimes.compareTo(new BigDecimal(0)) == 0) {
            dimeString = "dimes";
        } else {
            dimeString = "dime";
        }
        String quarterString = "";
        if (quarters.compareTo(new BigDecimal(1)) == 1) {
            quarterString = "quarters";
        } else {
            quarterString = "quarter";
        }


        UserOutput.displayMessage("Your change is " + quarters + " " + quarterString + ", " + dimes + " "
                + dimeString + ", and 1 nickel.");
        money.setBalance(new BigDecimal(0.00));
        return ("Your change is " + quarters + " " + quarterString + ", " + dimes + " " + dimeString + ", and 1 nickel.");
    }



}



