package ru.job4j.foodstorage;

import ru.job4j.foodstorage.food.FoodInterface;
import ru.job4j.foodstorage.movement.*;

import java.util.List;

public class ControlQuality {

    private final List<Mover> movers;

    public ControlQuality(List<Mover> movers) {
        this.movers = movers;
    }

    public void moveProducts(List<FoodInterface> listOfFood) {
        for (Mover eachMover : this.movers) {
            eachMover.move(listOfFood);
        }
    }
}
