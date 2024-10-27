package com.flower.flowerContinue.flower;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FlowerService {
    public List<Flower> getFlowers() {
        return List.of(
            new Flower(100, 20.0, FlowerType.CHAMOMILE),
            new Flower(200, 10.0, FlowerType.ROSE),
            new Flower(90, 25.0, FlowerType.TULIP),
            new Flower(500, 100.0, FlowerType.ROSE)
        );
    }
}
