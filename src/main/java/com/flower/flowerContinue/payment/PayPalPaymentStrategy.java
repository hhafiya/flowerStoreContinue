package com.flower.flowerContinue.payment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PayPalPaymentStrategy implements Payment {
    private String email;
    private double availableFunds;


    @Override
    public String pay(double amount) {
        if (availableFunds >= amount) {
            availableFunds -= amount;
            return ("Paying " + amount + " using PayPal account: " + email + ".");
        }
        else {
            return ("Insufficient funds for PayPal account.");
        }
    }

    public double getAvailableFunds() {
        return availableFunds; 
    }
}
