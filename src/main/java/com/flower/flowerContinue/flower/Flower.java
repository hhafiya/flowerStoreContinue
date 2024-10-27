package com.flower.flowerContinue.flower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Flower extends Item {
    private int sepalLength;
    private double price;
    private FlowerType flowerType;

    public Flower(Flower flower) {
        sepalLength = flower.sepalLength;
        price = flower.price;
        flowerType = flower.flowerType;
    }
}
