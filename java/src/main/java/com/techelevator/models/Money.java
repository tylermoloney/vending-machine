package com.techelevator.models;

import java.math.BigDecimal;

public class Money {
    private BigDecimal balance = new BigDecimal("0.00");


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public void makePurchase(BigDecimal itemPrice) {
        balance = balance.subtract(itemPrice);
    }


    public void addMoney(BigDecimal moneyAdded) {
        balance = balance.add(moneyAdded);
    }


}
