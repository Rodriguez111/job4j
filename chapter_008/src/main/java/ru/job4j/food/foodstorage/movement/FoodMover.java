package ru.job4j.food.foodstorage.movement;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.StorageInterface;


import java.util.ArrayList;
import java.util.List;

public abstract class FoodMover implements Mover {

    final StorageInterface storage;

    public FoodMover(StorageInterface storage) {
        this.storage = storage;
    }

    /**
     * Select products from the passed list according to criteria in checkExpireDate() method,
     * removes selected products from original list and returns list of selected products.
     *
     * @param listOfFood - list of advancedFood from which we select products according to criteria.
     * @return - returns list of selected products.
     */
    public List<Food> select(List<Food> listOfFood) {
        List<Food> temp = List.copyOf(listOfFood);
        List<Food> result = new ArrayList<>();
        for (Food eachFood : temp) {
            if (checkExpireDate(eachFood)) {
                result.add(eachFood);
                listOfFood.remove(eachFood);
            }
        }
        return result;
    }

    public void move(List<Food> listOfFood) {
        List<Food> result = select(listOfFood);
        if (result.size() > 0) {
            this.storage.addFood(result);
        }
    }

    @Override
    public void put(List<Food> listOfFood) {
        if (listOfFood.size() > 0) {
            this.storage.addFood(listOfFood);
        }
    }

    public StorageInterface getStorage() {
        return storage;
    }

    /**
     * Check if product's expire date corresponds to certain criteria.
     *
     * @param food - advancedFood item to check.
     * @return - true or false.
     */
   public abstract boolean checkExpireDate(Food food);

    /**
     * @param threshold  - percents amount of threshold.
     * @param daysAmount - shelf life in days.
     * @return - amount of days corresponding to percents amount.
     */
   public long getThreshold(int threshold, long daysAmount) {
        return threshold * daysAmount / 100;
    }
}
