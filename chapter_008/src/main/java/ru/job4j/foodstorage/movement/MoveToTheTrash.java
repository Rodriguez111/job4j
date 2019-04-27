package ru.job4j.foodstorage.movement;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;
import ru.job4j.foodstorage.storage.Storage;
import ru.job4j.foodstorage.storage.Trash;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MoveToTheTrash extends FoodMover {

    public MoveToTheTrash(Storage storage) {
        super(storage);
    }

    @Override
    public boolean checkExpireDate(FoodInterface food) {
        LocalDateTime today = LocalDateTime.now();
        return today.isAfter(food.getExpireDate());
    }
}
