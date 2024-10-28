package com.flower.flowerContinue.order;

import java.util.List;

import com.flower.flowerContinue.delivery.Delivery;
import com.flower.flowerContinue.flower.Flower;
import com.flower.flowerContinue.flower.Item;
import com.flower.flowerContinue.payment.Payment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private int orderId;
    private List<Flower> items;
    private Payment payment;
    private Delivery delivery;
    private boolean isPaymentSuccessful = false;
    
    public void setPaymentStrategy(Payment paymentSrategy) {
        payment = paymentSrategy;
    }
    public void setDeliveryStrategy(Delivery deliverySrategy) {
        delivery = deliverySrategy;
    }

    public void addItem(Flower item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public void processOrder() {
        double sum = calculateTotalPrice();
        payment.pay(sum);
        isPaymentSuccessful = true;
        delivery.deliver(this);
    }
}
