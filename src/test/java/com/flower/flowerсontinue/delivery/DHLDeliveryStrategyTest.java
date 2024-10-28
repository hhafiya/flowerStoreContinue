package com.flower.flowerсontinue.delivery;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.flower.flowerсontinue.delivery.DHLDeliveryStrategy;
import com.flower.flowerсontinue.flower.Flower;
import com.flower.flowerсontinue.flower.FlowerType;
import com.flower.flowerсontinue.order.Order;
import com.flower.flowerсontinue.payment.CreditCardPaymentStrategy;

@SpringBootTest
public class DHLDeliveryStrategyTest {
    
    private DHLDeliveryStrategy dhlDeliveryStrategy;
    private Order order;

    @BeforeEach
    void setUp() {
        dhlDeliveryStrategy = new DHLDeliveryStrategy();
        order = new Order();
        order.setOrderId(1);
        order.setPaymentStrategy(new CreditCardPaymentStrategy());
        order.setDeliveryStrategy(dhlDeliveryStrategy);
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(15, 23.0, FlowerType.CHAMOMILE));
        items.add(new Flower(10, 11.0, FlowerType.ROSE));
        items.add(new Flower(20, 30.0, FlowerType.TULIP));
        order.setItems(new ArrayList<>());
    }

    @Test
    void testDeliveryWithSuccessfulPayment() {
        order.processOrder();

        String result = assertDoesNotThrow(() -> dhlDeliveryStrategy.deliver(order));
        assertEquals("Delivering order #1 using DHL Delivery.", result);
    }

    @Test
    void testDeliveryWithUnsuccessfulPayment() {
        String result = assertDoesNotThrow(() -> dhlDeliveryStrategy.deliver(order));
        assertEquals("Cannot deliver order #1 due to unsuccessful payment.", result);
    }

}
