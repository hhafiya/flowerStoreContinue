package com.flower.flowerContinue.payment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreditCardPaymentStrategy implements Payment {
    private String cardHolderName;
    private double availableFunds;

    @Override
    public String pay(double amount) {
        if (availableFunds >= amount) {
            availableFunds -= amount;
            return ("Processing credit card payment for " + cardHolderName + " of amount " + amount + ".");
        } else {
            return ("Insufficient funds for Credit card payment.");
        }
    }

    public double getAvailableFunds() {
        return availableFunds; 
    }
}
