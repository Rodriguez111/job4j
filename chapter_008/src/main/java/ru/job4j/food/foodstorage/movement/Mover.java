package ru.job4j.food.foodstorage.movement;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.StorageInterface;

import java.util.List;

public interface Mover {
    List<Food> select(List<Food> listOfFood);

    void move(List<Food> listOfFood);

    void put(List<Food> listOfFood);

    StorageInterface getStorage();
}
