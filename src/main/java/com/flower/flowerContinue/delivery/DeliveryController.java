package com.flower.flowerContinue.delivery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.flowerContinue.flower.Flower;
import com.flower.flowerContinue.flower.Item;
import com.flower.flowerContinue.order.Order;

@RestController
public class DeliveryController {
    @GetMapping("/delivery")
    public String getDelivery(Order order, Delivery delivery) {
        List<Item> items = new ArrayList<>();
        items.add(new Flower());
        items.add(new Flower());
        delivery.deliver(order);
        return "Delivery completed";
    }
}
