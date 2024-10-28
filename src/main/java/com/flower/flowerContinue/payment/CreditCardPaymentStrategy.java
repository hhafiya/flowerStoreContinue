package com.flower.flowerContinue.payment;

import lombok.Setter;

@Setter
public class CreditCardPaymentStrategy implements Payment {
    private String cardHolderName;

    @Override
    public String pay(double amount) {
        return ("Processing credit card payment for " + cardHolderName + " of amount " + amount + ".");
    }
}
