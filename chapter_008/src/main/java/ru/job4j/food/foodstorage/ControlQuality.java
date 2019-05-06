package ru.job4j.food.foodstorage;

import ru.job4j.food.advancedfoodstorage.AdvancedQControl;
import ru.job4j.food.advancedfoodstorage.Resorter;
import ru.job4j.food.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.food.advancedfoodstorage.food.FoodType;
import ru.job4j.food.advancedfoodstorage.movement.MoveToRecycle;
import ru.job4j.food.advancedfoodstorage.movement.MoveToWHRefrigerator;
import ru.job4j.food.advancedfoodstorage.storage.Recycle;
import ru.job4j.food.advancedfoodstorage.storage.RefrigeratorWareHouse;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.*;
import ru.job4j.food.foodstorage.storage.Shop;
import ru.job4j.food.foodstorage.storage.Trash;
import ru.job4j.food.foodstorage.storage.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Mover> movers;

    public ControlQuality(List<Mover> movers) {
        this.movers = movers;
    }

    public void moveProducts(List<Food> listOfFood) {
        for (Mover eachMover : this.movers) {
            if (listOfFood.size() == 0) {
                break;
            }
            eachMover.move(listOfFood);
        }
    }

    public static void main(String[] args) {

        LocalDateTime today = LocalDateTime.now();

        LocalDateTime milkExpire = today.plusDays(1);
        LocalDateTime milkCreateDate = today.minusDays(4);
        AdvancedFood advMilk = new AdvancedFood("Milk", milkExpire, milkCreateDate, 20, 40, true, FoodType.MILK);

        LocalDateTime cheeseExpire = today.minusDays(1);
        LocalDateTime cheeseCreateDate = today.minusDays(35);
        Food cheese = new Food("Cheddar", cheeseExpire, cheeseCreateDate, 52, 50);

        LocalDateTime bananaExpire = today.minusDays(2);
        LocalDateTime bananaCreateDate = today.minusDays(21);
        AdvancedFood advBanana = new AdvancedFood("Banana", bananaExpire, bananaCreateDate, 23, 50, true, FoodType.FRUITS);


        LocalDateTime tomatoExpire = today.plusDays(14);
        LocalDateTime tomatoCreateDate = today.minusDays(3);
        AdvancedFood advTomato = new AdvancedFood("Tomato", tomatoExpire, tomatoCreateDate, 18, 60, true,  FoodType.VEGETABLES);



        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(cheese);
        listOfFood.add(advMilk);
        listOfFood.add(advBanana);
        listOfFood.add(advTomato);


        Recycle recycle = new Recycle("Recycle");
        Trash trash = new Trash("Trash");
        RefrigeratorWareHouse refrigeratorWareHouse = new RefrigeratorWareHouse("RefrigeratorWareHouse");
        Shop shop = new Shop("Shop");
        Warehouse warehouse = new Warehouse("Warehouse");



        MoveToRecycle moveToRecycle = new MoveToRecycle(new MoveToTheTrash(recycle));
        MoveToWHRefrigerator moveToWHRefrigerator = new MoveToWHRefrigerator(new MoveToTheWareHouse(refrigeratorWareHouse));
        MoveToTheShop moveToTheShop = new MoveToTheShop(shop);
        MoveToTheShopDisc moveToTheShopDisc = new MoveToTheShopDisc(shop);
        MoveToTheTrash moveToTheTrash = new MoveToTheTrash(trash);
        MoveToTheWareHouse moveToTheWareHouse = new MoveToTheWareHouse(warehouse);

        List<Mover> movers = List.of(moveToRecycle, moveToWHRefrigerator, moveToTheShop, moveToTheShopDisc, moveToTheTrash, moveToTheWareHouse);
        //List<Mover> movers = List.of(moveToTheShop);
        //ControlQuality controlQuality = new ControlQuality(movers);
        ControlQuality controlQuality = new AdvancedQControl(movers, new Resorter(movers));
        controlQuality.moveProducts(listOfFood);

        recycle.printBalance();
        refrigeratorWareHouse.printBalance();
        shop.printBalance();
        trash.printBalance();
        warehouse.printBalance();


        ((AdvancedQControl) controlQuality).resort();

        System.out.println("--------==========************=========----------");

        recycle.printBalance();
        refrigeratorWareHouse.printBalance();
        shop.printBalance();
        trash.printBalance();
        warehouse.printBalance();


    }

}
