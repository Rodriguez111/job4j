package ru.job4j.food.foodstorage.storage;

import ru.job4j.food.foodstorage.food.Food;

import java.util.List;

public interface StorageInterface {
    void addFood(List<Food> listOfFood);

    List<Food> withdrawFood();

    void printBalance();
}
