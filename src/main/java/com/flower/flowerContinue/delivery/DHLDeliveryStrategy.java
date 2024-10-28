package com.flower.flowerContinue.delivery;

import com.flower.flowerContinue.order.Order;

public class DHLDeliveryStrategy implements Delivery{
    @Override
    public String deliver(Order order) {
        if (order.isPaymentSuccessful()) {
            return ("Delivering order #" + order.getOrderId() + " using DHL Delivery.");
        } else {
            return ("Cannot deliver order #" + order.getOrderId() + " due to unsuccessful payment.");
        }
    }
}
