package com.flower.flowerсontinue.payment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flower.flowerсontinue.payment.PayPalPaymentStrategy;

public class PayPalPaymentStrategyTest {
    private PayPalPaymentStrategy payPalPaymentStrategy;

    @BeforeEach
    void setUp() {
        payPalPaymentStrategy = new PayPalPaymentStrategy();
        payPalPaymentStrategy.setEmail("john.doe@example.com");
    }

    @Test
    void testPay() {
        double amount = 75.0;
        String result = assertDoesNotThrow(() -> payPalPaymentStrategy.pay(amount));
        assertEquals("Paying 75.0 using PayPal account: john.doe@example.com.", result);
    }

    @Test
    void testPayWithZeroAmount() {
        double amount = 0.0;
        String result = assertDoesNotThrow(() -> payPalPaymentStrategy.pay(amount));
        assertEquals("Paying 0.0 using PayPal account: john.doe@example.com.", result);
    }
}
