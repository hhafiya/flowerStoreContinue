package com.flower.flowerContinue.business;

import java.util.List;

import com.flower.flowerContinue.flower.Flower;
import com.flower.flowerContinue.flower.Item;
import com.flower.flowerContinue.payment.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Order {
    private int orderId;
    private List<Flower> items;
    private Payment paymentStrategy;
    private boolean paymentSuccessful;
    
    public void addItem(Flower item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public void setPaymentSuccessful(boolean b) {
        paymentSuccessful = b;
    }
}
