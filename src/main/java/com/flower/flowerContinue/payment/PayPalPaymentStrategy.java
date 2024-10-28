package com.flower.flowerContinue.payment;

import lombok.Setter;

@Setter
public class PayPalPaymentStrategy implements Payment {
    private String email;

    @Override
    public String pay(double amount) {
        return ("Paying " + amount + " using PayPal account: " + email + ".");
    }
}
