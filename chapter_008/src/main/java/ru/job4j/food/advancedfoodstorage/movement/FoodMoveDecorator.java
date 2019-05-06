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

    @Override
    public List<Food> select(List<Food> listOfFood) {
        List<Food> select = mover.select(listOfFood);
        List<Food> result = new ArrayList<>();
        for (Food eachFood : select) {
            try {
                if (checkCondition((AdvancedFood) eachFood)) {
                    result.add(eachFood);
                } else {
                    listOfFood.add(eachFood);
                }
            } catch (ClassCastException e) {
                listOfFood.add(eachFood);
            }
        }
        return result;
    }

    @Override
    public void move(List<Food> listOfFood) {
        List<Food> select = select(listOfFood);
        if (select.size() > 0) {
            put(select);
        }

    }

    @Override
    public StorageInterface getStorage() {
        return this.mover.getStorage();
    }

    @Override
    public void put(List<Food> listOfFood) {
        mover.put(listOfFood);
    }

    public abstract boolean checkCondition(AdvancedFood food);

}
