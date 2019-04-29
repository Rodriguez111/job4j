package ru.job4j.foodstorage.movement;

import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.storage.Storage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class MoveToTheShopDisc extends FoodMover {
    private final static int HIGHER_PERCENTAGE_THRESHOLD = 75;

    public MoveToTheShopDisc(Storage storage) {
        super(storage);
    }

    @Override
    public void move(List<Food> listOfFood) {
        List<Food> result = super.select(listOfFood);
        for (Food eachFood : result) {
            eachFood.setPrice(eachFood.getPrice() - (eachFood.getPrice() * eachFood.getDiscount() / 100));
        }
        this.storage.addFood(result);
    }

    @Override
    public boolean checkExpireDate(Food food) {
        LocalDateTime today = LocalDateTime.now();
        Duration lifetime = Duration.between(food.getCreateDate(), food.getExpireDate());
        long higherThreshold = getThreshold(HIGHER_PERCENTAGE_THRESHOLD, lifetime.toDays());
        Duration daysPassed = Duration.between(food.getCreateDate(), today);
        return daysPassed.toDays() > higherThreshold && (today.isBefore(food.getExpireDate()) || today.isEqual(food.getExpireDate()));
    }
}
