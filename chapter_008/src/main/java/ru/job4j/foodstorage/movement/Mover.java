package ru.job4j.foodstorage.movement;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;

import java.util.List;

public interface Mover {
    List<FoodInterface> select(List<FoodInterface> listOfFood);

    void move(List<FoodInterface> listOfFood);

}
