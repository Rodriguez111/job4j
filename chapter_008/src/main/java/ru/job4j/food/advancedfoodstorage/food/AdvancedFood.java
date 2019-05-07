package ru.job4j.food.advancedfoodstorage.food;

import ru.job4j.food.foodstorage.food.Food;

import java.time.LocalDateTime;

public class AdvancedFood extends Food {
    private boolean isRecyclable;

    private FoodType type;


    public AdvancedFood(String name, LocalDateTime expireDate,
                        LocalDateTime createDate, double price,
                        int discount, boolean isRecyclable, FoodType type) {
        super(name, expireDate, createDate, price, discount);
        this.isRecyclable = isRecyclable;
        this.type = type;
    }

    public boolean isRecyclable() {
        return isRecyclable;
    }


    public FoodType getType() {
        return type;
    }
}
