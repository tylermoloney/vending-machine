package com.techelevator.ui;

import com.techelevator.models.Items;
import com.techelevator.models.Logger;
import com.techelevator.models.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * <p>
 * Dependencies: None
 */
public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("1) Display Vending Machine Items");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        switch (option) {
            case "1":
                return "display";
            case "2":

                return "purchase";
            case "3":
                return "exit";
            case "4":
                return "sales report";
            default:
                return "";
        }
    }

    public static String getPurchaseOption(Money money) {

        UserOutput.displayMessage("(1) Feed Money\n(2) Select Product\n(3) Finish Transaction");


        UserOutput.displayMessage("Current Money Provided: $" + money.getBalance());
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        //Returning strings now to test menu functionality
        switch (option) {
            case "1":
                return "Feed Money";
            case "2":
                return "Select Product";
            case "3":
                return "Finish Transaction";
            default:
                return "";
        }

    }

    public static String receiveMoney() {
        UserOutput.displayMessage("Please insert a $1, $2, $5, or $10 bill");
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        if (!option.equals("1") && !option.equals("2") && !option.equals("5") && !option.equals("10")) {
            UserOutput.displayMessage("Invalid credits.");
            return "Invalid credits.";
        } else {

            switch (option) {
                case "1":
                    return "1";
                case "2":
                    return "2";
                case "5":
                    return "5";
                case "10":
                    return "10";
            }
        }
        return option;
    }

    public static String makePurchase(List<Items> items) {
        UserOutput.displayMessage("Make a selection: ");
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();
        for (int i = 0; i < items.size(); i++) {
            if (option.equals(items.get(i).getLocation())) {
                return String.valueOf(i);
            }
        }
        return ("x");
    }
}




