package ru.job4j.food.foodstorage.movement;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.StorageInterface;

import java.time.Duration;
import java.time.LocalDateTime;


public class MoveToTheWareHouse extends FoodMover {
    private final static int LOWER_PERCENTAGE_THRESHOLD = 25;

    public MoveToTheWareHouse(StorageInterface storage) {
        super(storage);
    }

    @Override
    public boolean checkExpireDate(Food food) {
        LocalDateTime today = LocalDateTime.now();
        Duration shelfLife = Duration.between(food.getCreateDate(), food.getExpireDate());
        long threshold = getThreshold(LOWER_PERCENTAGE_THRESHOLD, shelfLife.toDays());
        Duration daysPassed = Duration.between(food.getCreateDate(), today);
        return daysPassed.toDays() < threshold;
    }
}
