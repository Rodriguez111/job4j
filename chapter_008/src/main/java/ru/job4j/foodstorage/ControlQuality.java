package ru.job4j.foodstorage;

import ru.job4j.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.advancedfoodstorage.movement.MoveToRecycle;
import ru.job4j.advancedfoodstorage.storage.Recycle;
import ru.job4j.advancedfoodstorage.storage.Warehouse2;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;
import ru.job4j.foodstorage.movement.*;
import ru.job4j.foodstorage.storage.Trash;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Mover> movers;

    public ControlQuality(List<Mover> movers) {
        this.movers = movers;
    }

    public void moveProducts(List<FoodInterface> listOfFood) {
        for (Mover eachMover : this.movers) {
            eachMover.move(listOfFood);
        }
    }

    public static void main(String[] args) {

        LocalDateTime today = LocalDateTime.now();

        LocalDateTime cheeseExpire = today.minusDays(1);
        LocalDateTime cheeseCreateDate = today.minusDays(35);
        Food cheese = new Food("Cheddar", cheeseExpire, cheeseCreateDate, 52, 50);

        LocalDateTime bananaExpire = today.minusDays(2);
        LocalDateTime bananaCreateDate = today.minusDays(21);
        Food banana = new Food("Banana", bananaExpire, bananaCreateDate, 23, 50);
        AdvancedFood advBanana = new AdvancedFood(banana, true);

        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food milk1 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 20, 50);

        List<FoodInterface> listOfFood = new ArrayList<>();
        listOfFood.add(cheese);
        listOfFood.add(milk1);
        listOfFood.add(advBanana);


        Recycle recycle = new Recycle("Recycle");
        Trash trash = new Trash("Trash");
        MoveToRecycle moveToRecycle = new MoveToRecycle(new MoveToTheTrash(recycle));
        List<Mover> movers = List.of(moveToRecycle);
        ControlQuality controlQuality = new ControlQuality(movers);
        controlQuality.moveProducts(listOfFood);

        recycle.printBalance();
    }

}
