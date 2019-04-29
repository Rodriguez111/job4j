package ru.job4j.food.advancedfoodstorage.movement;

import ru.job4j.food.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.food.advancedfoodstorage.food.FoodType;

import ru.job4j.food.foodstorage.movement.MoveToTheWareHouse;


public class MoveToWHRefrigerator extends FoodMoveDecorator {
    public MoveToWHRefrigerator(MoveToTheWareHouse mover) {
        super(mover);
    }

    @Override
    public boolean checkCondition(AdvancedFood food) {
        return food.getType().equals(FoodType.VEGETABLES);
    }
}
