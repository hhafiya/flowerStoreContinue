package com.flower.flowerContinue.business;
import com.flower.flowerContinue.delivery.Delivery;
import com.flower.flowerContinue.flower.Flower;
import com.flower.flowerContinue.payment.Payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    
    public String processPayment(int orderId, List<Flower> items, Payment paymentStrategy, Delivery deliveryStrategy) {
        Order order = new Order(orderId, items, paymentStrategy, false);
        double totalPrice = order.calculateTotalPrice();
        String message = paymentStrategy.pay(totalPrice);
        if (message == "Insufficient funds for PayPal account." ||
        message == "Insufficient funds for Credit card payment."){
            order.setPaymentSuccessful(false);
            String deliveryMessage = deliverOrder(order, deliveryStrategy);
            return message + deliveryMessage;
        }
        order.setPaymentSuccessful(true);
        String deliveryMessage = deliverOrder(order, deliveryStrategy);
        return message + " Order created and payment processed successfully. " + deliveryMessage;
    }
    private String deliverOrder(Order order, Delivery deliveryStrategy) {
        return deliveryStrategy.deliver(order);
    }
}