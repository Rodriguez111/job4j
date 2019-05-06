package ru.job4j.food.foodstorage.movement;

import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.StorageInterface;

import java.time.LocalDateTime;

public class MoveToTheTrash extends FoodMover {

    public MoveToTheTrash(StorageInterface storage) {
        super(storage);
    }

    @Override
    public boolean checkExpireDate(Food food) {
        LocalDateTime today = LocalDateTime.now();
        return today.isAfter(food.getExpireDate());
    }
}
