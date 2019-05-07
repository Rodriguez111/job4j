package ru.job4j.food.advancedfoodstorage.movement;

import ru.job4j.food.advancedfoodstorage.food.AdvancedFood;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.Mover;
import ru.job4j.food.foodstorage.storage.StorageInterface;


import java.util.ArrayList;
import java.util.List;

public abstract class FoodMoveDecorator implements Mover {
    public final Mover mover;

    public FoodMoveDecorator(Mover mover) {
        this.mover = mover;
    }


    public void moveAdvancedFood(List<AdvancedFood> listOfFood) {
        List<AdvancedFood> temp = List.copyOf(listOfFood);
        List<Food> result = new ArrayList<>();
        for (AdvancedFood eachFood : temp) {
            if (checkCondition(eachFood)) {
                result.add(eachFood);
                listOfFood.remove(eachFood);
            }
        }
      mover.move(result);
    }

    @Override
    public List<Food> select(List<Food> listOfFood) {
        return null;
    }

    @Override
    public void move(List<Food> listOfFood) {

    }

    @Override
    public StorageInterface getStorage() {
        return this.mover.getStorage();
    }

    public abstract boolean checkCondition(AdvancedFood food);
}
