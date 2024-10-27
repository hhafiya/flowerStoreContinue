package com.flower.flowerContinue.delivery;

import com.flower.flowerContinue.business.Order;

public class PostDeliveryStrategy implements Delivery{
    @Override
    public String deliver(Order order) {
        if (order.isPaymentSuccessful()) {
            return ("Delivering order #" + order.getOrderId() + " using Post Delivery.");
        } else {
            return ("Cannot deliver order #" + order.getOrderId() + " due to unsuccessful payment.");
        }
    }
}
