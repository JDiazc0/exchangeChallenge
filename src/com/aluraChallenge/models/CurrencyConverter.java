package com.aluraChallenge.models;

public class CurrencyConverter {
    private String name;
    private double value;
    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyConverter(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double convertAmount(CurrencyConverter currency){
        return this.amount * currency.value;
    }

}

