package ru.job4j.food.advancedfoodstorage.movement;


import ru.job4j.food.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.MoveToTheTrash;

import java.util.List;


public class MoveToRecycle extends FoodMoveDecorator {

    public MoveToRecycle(MoveToTheTrash mover) {
        super(mover);
    }

    @Override
    public boolean checkCondition(AdvancedFood food) {
        return food.isRecyclable();
    }

    @Override
    public void move(List<Food> listOfFood) {
        List<Food> select = select(listOfFood);
        if (select.size() > 0) {
            super.put(select);
        }
    }
}
