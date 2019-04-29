package ru.job4j.foodstorage.movement;

import ru.job4j.foodstorage.food.Food;

import java.util.List;

public interface Mover {
    List<Food> select(List<Food> listOfFood);

    void move(List<Food> listOfFood);

    void put(List<Food> listOfFood);
}
