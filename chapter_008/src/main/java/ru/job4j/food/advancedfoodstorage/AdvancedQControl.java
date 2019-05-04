package ru.job4j.food.advancedfoodstorage;

import ru.job4j.food.foodstorage.ControlQuality;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.Mover;

import java.util.List;

public class AdvancedQControl extends ControlQuality {
    private final Resortable resorter;

    public AdvancedQControl(List<Mover> movers, Resortable resorter) {
        super(movers);
        this.resorter = resorter;
    }

    @Override
    public void moveProducts(List<Food> listOfFood) {
        super.moveProducts(listOfFood);
    }

    public void resort() {
        resorter.resort();
    }


}
