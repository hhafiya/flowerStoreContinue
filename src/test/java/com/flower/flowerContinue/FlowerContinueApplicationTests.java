package com.flower.flowerContinue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.flower.flowerContinue.business.Order;
import com.flower.flowerContinue.business.OrderService;
import com.flower.flowerContinue.delivery.DHLDeliveryStrategy;
import com.flower.flowerContinue.delivery.Delivery;
import com.flower.flowerContinue.delivery.PostDeliveryStrategy;
import com.flower.flowerContinue.flower.Flower;
import com.flower.flowerContinue.flower.FlowerType;
import com.flower.flowerContinue.payment.CreditCardPaymentStrategy;
import com.flower.flowerContinue.payment.PayPalPaymentStrategy;
import com.flower.flowerContinue.payment.Payment;


@SpringBootTest
@AutoConfigureMockMvc
class FlowerContinueApplicationTests {
    private Order order;
    private Payment paymentStrategy;


    private OrderService orderService;
	private Delivery postDeliveryStrategy;
    private Delivery dhlDeliveryStrategy;

    @BeforeEach
    void setUp() {
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(100, 20.0, FlowerType.CHAMOMILE));
        items.add(new Flower(200, 10.0, FlowerType.ROSE));
        
        paymentStrategy = new CreditCardPaymentStrategy("Test Card Holder", 1000.0);
        order = new Order(1, items, paymentStrategy, false);
        orderService = new OrderService();

		postDeliveryStrategy = new PostDeliveryStrategy();
        dhlDeliveryStrategy = new DHLDeliveryStrategy();
    }

    @Test
    void testAddItem() {
        Flower newFlower = new Flower(90, 25.0, FlowerType.TULIP);
        order.addItem(newFlower);

        assertEquals(3, order.getItems().size());
        assertTrue(order.getItems().contains(newFlower));
    }

    @Test
    void testCalculateTotalPrice() {
        double totalPrice = order.calculateTotalPrice();
        assertEquals(30.0, totalPrice);
    }




	@Test
    void testProcessCreditCardPayment1() {
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(100, 20.0, FlowerType.CHAMOMILE));
        items.add(new Flower(200, 10.0, FlowerType.ROSE));
        
        Payment paymentStrategy = new CreditCardPaymentStrategy("Test Card Holder", 100.0);

        String result = orderService.processPayment(1, items, paymentStrategy, dhlDeliveryStrategy);

        assertEquals("Processing credit card payment for Test Card Holder of amount 30.0. Order created and payment processed successfully. Delivering order #1 using DHL Delivery.", result);
        double remainingFunds = ((CreditCardPaymentStrategy) paymentStrategy).getAvailableFunds();
        assertEquals(70.0, remainingFunds, "Available funds should be deducted correctly after payment.");
    }

	@Test
    void testProcessCreditCardPayment2() {
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(100, 20.0, FlowerType.CHAMOMILE));
        items.add(new Flower(200, 10.0, FlowerType.ROSE));
        
        Payment paymentStrategy = new CreditCardPaymentStrategy("Test Card Holder", 10.0);

        String result = orderService.processPayment(1, items, paymentStrategy, postDeliveryStrategy);

        assertEquals("Insufficient funds for Credit card payment.Cannot deliver order #1 due to unsuccessful payment.", result);
        double remainingFunds = ((CreditCardPaymentStrategy) paymentStrategy).getAvailableFunds();
        assertEquals(10.0, remainingFunds, "Available funds should be deducted correctly after payment.");
    }

	@Test
    void testProcessPayPalPayment1() {
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(200, 500.0, FlowerType.CHAMOMILE));
        items.add(new Flower(500, 100.0, FlowerType.ROSE));

        
        Payment paymentStrategy = new PayPalPaymentStrategy("Test Card Holder", 1000.0);

        String result = orderService.processPayment(1, items, paymentStrategy, postDeliveryStrategy);
        assertEquals("Paying 600.0 using PayPal account: Test Card Holder. Order created and payment processed successfully. Delivering order #1 using Post Delivery.", result);
        double remainingFunds = ((PayPalPaymentStrategy) paymentStrategy).getAvailableFunds();
        assertEquals(400.0, remainingFunds, "Available funds should be deducted correctly after payment.");
    }

	@Test
    void testProcessPayPalPayment2() {
        List<Flower> items = new ArrayList<>();
        items.add(new Flower(200, 500.0, FlowerType.CHAMOMILE));
        items.add(new Flower(500, 100.0, FlowerType.ROSE));

        
        Payment paymentStrategy = new PayPalPaymentStrategy("Test Card Holder", 100.0);

        String result = orderService.processPayment(1, items, paymentStrategy, postDeliveryStrategy);
        assertEquals("Insufficient funds for PayPal account.Cannot deliver order #1 due to unsuccessful payment.", result);
        double remainingFunds = ((PayPalPaymentStrategy) paymentStrategy).getAvailableFunds();
        assertEquals(100.0, remainingFunds, "Available funds should be deducted correctly after payment.");

    }
}
