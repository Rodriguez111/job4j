package ru.job4j.foodstorage.movement;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.Storage;

import java.time.LocalDateTime;

public class MoveToTheTrash extends FoodMover {

    public MoveToTheTrash(Storage storage) {
        super(storage);
    }

    @Override
    public boolean checkExpireDate(Food food) {
        LocalDateTime today = LocalDateTime.now();
        return today.isAfter(food.getExpireDate());
    }
}
