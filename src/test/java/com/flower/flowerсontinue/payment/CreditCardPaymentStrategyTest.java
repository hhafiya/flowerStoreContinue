package com.flower.flowerсontinue.payment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flower.flowerсontinue.payment.CreditCardPaymentStrategy;

public class CreditCardPaymentStrategyTest {
    private CreditCardPaymentStrategy creditCardPaymentStrategy;

    @BeforeEach
    void setUp() {
        creditCardPaymentStrategy = new CreditCardPaymentStrategy();
        creditCardPaymentStrategy.setCardHolderName("John");
    }

    @Test
    void testPay() {
        double amount = 50.0;
        String result = assertDoesNotThrow(() -> creditCardPaymentStrategy.pay(amount));
        assertEquals("Processing credit card payment for John of amount 50.0.", result);
    }

    @Test
    void testPayWithZeroAmount() {
        double amount = 0.0;
        String result = assertDoesNotThrow(() -> creditCardPaymentStrategy.pay(amount));
        assertEquals("Processing credit card payment for John of amount 0.0.", result);
    }
}
