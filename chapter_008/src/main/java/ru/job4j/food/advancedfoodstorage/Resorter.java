package ru.job4j.food.advancedfoodstorage;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.Mover;

import java.util.ArrayList;
import java.util.List;

public class Resorter implements Resortable {
    private final List<Food> listOfFood = new ArrayList<>();
    private final List<Mover> movers;

    public Resorter(List<Mover> movers) {
        this.movers = movers;
    }

    @Override
    public void gather() {
        for (Mover eachMover : this.movers) {
            listOfFood.addAll(eachMover.getStorage().withdrawFood());
        }
    }

    @Override
    public void resort() {
        gather();
        for (Mover eachMover : this.movers) {
            if (listOfFood.size() == 0) {
                break;
            }
            eachMover.move(listOfFood);
        }
    }
}
