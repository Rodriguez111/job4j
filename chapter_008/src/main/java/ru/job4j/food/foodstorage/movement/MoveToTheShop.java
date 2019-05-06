package ru.job4j.food.foodstorage.movement;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.StorageInterface;


import java.time.Duration;
import java.time.LocalDateTime;


public class MoveToTheShop extends FoodMover {
    private final static int LOWER_PERCENTAGE_THRESHOLD = 25;
    private final static int HIGHER_PERCENTAGE_THRESHOLD = 75;

    public MoveToTheShop(StorageInterface storage) {
        super(storage);
    }

    @Override
    public boolean checkExpireDate(Food food) {
        LocalDateTime today = LocalDateTime.now();
        Duration lifetime = Duration.between(food.getCreateDate(), food.getExpireDate());
        long lowerThreshold = getThreshold(LOWER_PERCENTAGE_THRESHOLD, lifetime.toDays());
        long higherThreshold = getThreshold(HIGHER_PERCENTAGE_THRESHOLD, lifetime.toDays());
        Duration daysPassed = Duration.between(food.getCreateDate(), today);
        return daysPassed.toDays() >= lowerThreshold && daysPassed.toDays() <= higherThreshold;
    }

}
