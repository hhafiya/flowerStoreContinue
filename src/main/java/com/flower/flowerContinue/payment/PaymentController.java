package com.flower.flowerContinue.payment;

import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;

import com.flower.flowerContinue.business.OrderService;
import com.flower.flowerContinue.delivery.DHLDeliveryStrategy;
import com.flower.flowerContinue.delivery.Delivery;
import com.flower.flowerContinue.delivery.PostDeliveryStrategy;
import com.flower.flowerContinue.flower.Flower;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class PaymentController {

    private final OrderService orderService;

    @PostMapping("/create")
    public String createOrder(@RequestParam String paymentType,
    @RequestParam String deliveryType,
                               @RequestParam int orderId,
                               @RequestBody List<Flower> items) {
        Payment paymentStrategy = determinePaymentStrategy(paymentType);
        if (paymentStrategy == null) {
            return "Invalid payment type.";
        }

        Delivery deliveryStrategy = determineDeliveryStrategy(deliveryType);
        if (deliveryStrategy == null) {
            return "Invalid delivery type.";
        }

        return orderService.processPayment(orderId, items, paymentStrategy, deliveryStrategy);
    }

    private Payment determinePaymentStrategy(String paymentType) {
        if ("creditCard".equalsIgnoreCase(paymentType)) {
            return new CreditCardPaymentStrategy("Default Card Holder", 1000.0);
        } else if ("paypal".equalsIgnoreCase(paymentType)) {
            return new PayPalPaymentStrategy("user@example.com", 1000.0);
        } else {
            return null;
        }
    }

    private Delivery determineDeliveryStrategy(String deliveryType) {
        if ("dhl".equalsIgnoreCase(deliveryType)) {
            return new DHLDeliveryStrategy();
        } else if ("post".equalsIgnoreCase(deliveryType)) {
            return new PostDeliveryStrategy();
        } else {
            return null;
        }
    }
}
