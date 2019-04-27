package ru.job4j.foodstorage.storage;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {
    private final static String BALANCE = "Balance of products on ";
    private final static String SINGLE_DELIMITER = "----------------------";
    private final static String DOUBLE_DELIMITER = "======================";
    private final String name;
    private final List<FoodInterface> listOfFood = new ArrayList<>();

    public Storage(String name) {
        this.name = name;
    }




    public void addFood(List<FoodInterface> listOfFood) {
        this.listOfFood.addAll(listOfFood);
    }

    /**
     * Print balance of products in storage to console.
     */
    public void printBalance() {
        System.out.println(BALANCE + name);
        System.out.println(SINGLE_DELIMITER);
        for (FoodInterface eachFood : this.listOfFood) {
            System.out.println(eachFood);
        }
        System.out.println(DOUBLE_DELIMITER);
    }

}
