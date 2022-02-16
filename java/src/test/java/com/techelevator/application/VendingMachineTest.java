package com.techelevator.application;

import com.techelevator.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineTest {

    @Before
    public void setup() {
        FileReader fileReader = new FileReader();
        Inventory inventory = new Inventory(fileReader.readFile());
        Logger logger = new Logger();
    }

    @Test
    public void receiveMoney_1_should_add_1_to_moneyBalance() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.receiveMoney("1");
        BigDecimal expected = new BigDecimal("1.00");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void receiveMoney_2_should_add_2_to_moneyBalance() {
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.receiveMoney("2");
        BigDecimal expected = new BigDecimal("2.00");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void receiveMoney_5_should_add_5_to_moneyBalance() {
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.receiveMoney("5");
        BigDecimal expected = new BigDecimal("5.00");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void receiveMoney_10_should_add_10_to_moneyBalance() {
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.receiveMoney("10");
        BigDecimal expected = new BigDecimal("10.00");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void receiveMoney_10_with_balance_5_should_return_15(){
        VendingMachine vendingMachine = new VendingMachine();
        Money money = new Money();
        money.setBalance(new BigDecimal(5.00));
        BigDecimal actual = money.getBalance().add(vendingMachine.receiveMoney("10"));
        BigDecimal expected = new BigDecimal("15.00");
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void makePurchase_option_x_should_return_Invalid_Entry(){
        VendingMachine vendingMachine = new VendingMachine();
        FileReader fileReader = new FileReader();
        Inventory inventory = new Inventory(fileReader.readFile());
        List<Items> list = inventory.getInventoryList();
        String actual = vendingMachine.makePurchase(list, "x");
        String expected = "Invalid Entry!";
        Assert.assertEquals(expected, actual);
    }
//    @Test
//    public void makePurchase_option_0_should_return_Potato_Crisps() {
//        VendingMachine vendingMachine = new VendingMachine();
//        FileReader fileReader = new FileReader();
//        Inventory inventory = new Inventory(fileReader.readFile());
//        List<Items> list = inventory.getInventoryList();
//        Money money = new Money();
//        //this setBalance isn't carrying into vendingMachine.makePurchase()
//        money.setBalance(new BigDecimal("5.85"));
//        String actual = vendingMachine.makePurchase(list, "0");
//        String expected = "Potato Crisps";
//        Assert.assertEquals(expected, actual);
//    }

    @Test
    public void makePurchase_option_0_sold_out_should_return_Sold_Out(){
        VendingMachine vendingMachine = new VendingMachine();
        FileReader fileReader = new FileReader();
        Inventory inventory = new Inventory(fileReader.readFile());
        List<Items> list = inventory.getInventoryList();
        Money money = new Money();
        list.get(0).setQuantity(0);
        String actual = vendingMachine.makePurchase(list, "0");
        String expected = "Sold Out";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void makePurchase_0_Balance_should_return_Insufficent_Funds() {
        VendingMachine vendingMachine = new VendingMachine();
        FileReader fileReader = new FileReader();
        Inventory inventory = new Inventory(fileReader.readFile());
        List<Items> list = inventory.getInventoryList();
        Money money = new Money();
        String actual = vendingMachine.makePurchase(list, "0");
        String expected = "Insufficient Funds!";
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void dispenseChange_0_returns_0_quarters(){
        VendingMachine vendingMachine = new VendingMachine();
        Money money = new Money();
        String actual = vendingMachine.dispenseChange(money);
        String expected = "Your change is 0 quarters";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void dispenseChange_85_returns_3_quarters_1_dime(){
        VendingMachine vendingMachine = new VendingMachine();
        Money money = new Money();
        money.setBalance(new BigDecimal("0.85"));
        String actual = vendingMachine.dispenseChange(money);
        String expected = "Your change is 3 quarters and 1 dime.";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void dispenseChange_115_returns_4_quarters_1_dime_1_nickel(){
        VendingMachine vendingMachine = new VendingMachine();
        Money money = new Money();
        money.setBalance(new BigDecimal("1.15"));
        String actual = vendingMachine.dispenseChange(money);
        String expected = "Your change is 4 quarters, 1 dime, and 1 nickel.";
        Assert.assertEquals(expected,actual);
    }

}