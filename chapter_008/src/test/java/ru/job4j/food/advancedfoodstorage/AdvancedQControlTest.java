package ru.job4j.food.advancedfoodstorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.*;
import ru.job4j.food.foodstorage.storage.Shop;
import ru.job4j.food.foodstorage.storage.Trash;
import ru.job4j.food.foodstorage.storage.Warehouse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdvancedQControlTest {
    private final static String BALANCE = "Balance of products on ";
    private final static String SINGLE_DELIMITER = "----------------------";
    private final static String DOUBLE_DELIMITER = "======================";
    private final static String LS = System.lineSeparator();

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private InputStream originalInput;
    private PrintStream originalStream;
    private PrintStream newStream;

    @Before
    public void before() {
        originalStream = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @After
    public void after() {
        System.setOut(originalStream);
        System.setIn(originalInput);
    }

    public String printTestOutput(List<Food> listOfFood, String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BALANCE + name)
                .append(LS)
                .append(SINGLE_DELIMITER)
                .append(LS);
        for (Food eachFood : listOfFood) {
            stringBuilder.append(eachFood)
                    .append(LS);
        }
        stringBuilder.append(DOUBLE_DELIMITER)
                .append(LS);
        return stringBuilder.toString();
    }

    public List<Food> generateFoodBeforeMove() {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food milk1 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 20, 50);

        LocalDateTime beefExpire = today.plusDays(7);
        LocalDateTime beefCreateDate = today.minusDays(7);
        Food beef = new Food("Beef", beefExpire, beefCreateDate, 50, 30);

        LocalDateTime porkExpire = today.plusDays(2);
        LocalDateTime porkCreateDate = today.minusDays(8);
        Food pork = new Food("Pork", porkExpire, porkCreateDate, 45, 35);

        LocalDateTime cheeseExpire = today.minusDays(1);
        LocalDateTime cheeseCreateDate = today.minusDays(35);
        Food cheese = new Food("Cheddar", cheeseExpire, cheeseCreateDate, 52, 50);

        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(milk1);
        listOfFood.add(beef);
        listOfFood.add(pork);
        listOfFood.add(cheese);
        return listOfFood;
    }

    public List<Food> generateFoodAfterMove() {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food milk1 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 20, 50);

        LocalDateTime beefExpire = today.plusDays(7);
        LocalDateTime beefCreateDate = today.minusDays(7);
        Food beef = new Food("Beef", beefExpire, beefCreateDate, 50, 30);

        LocalDateTime porkExpire = today.plusDays(2);
        LocalDateTime porkCreateDate = today.minusDays(8);
        Food pork = new Food("Pork", porkExpire, porkCreateDate, 29.25, 35);

        LocalDateTime cheeseExpire = today.minusDays(1);
        LocalDateTime cheeseCreateDate = today.minusDays(35);
        Food cheese = new Food("Cheddar", cheeseExpire, cheeseCreateDate, 52, 50);

        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(milk1);
        listOfFood.add(beef);
        listOfFood.add(pork);
        listOfFood.add(cheese);
        return listOfFood;
    }


    public List<Mover> generateMovers() {
        Warehouse warehouse = new Warehouse("Warehouse");
        FoodMover foodMoverToWH = new MoveToTheWareHouse(warehouse);

        Shop shop = new Shop("Shop");
        FoodMover foodMoverToShop = new MoveToTheShop(shop);

        FoodMover foodMoverToShopWithDisc = new MoveToTheShopDisc(shop);

        Trash trash = new Trash("Trash");
        FoodMover foodMoverToTrash = new MoveToTheTrash(trash);

        List<Mover> listOfMovers = new ArrayList<>();
        listOfMovers.add(foodMoverToWH);
        listOfMovers.add(foodMoverToShop);
        listOfMovers.add(foodMoverToShopWithDisc);
        listOfMovers.add(foodMoverToTrash);
        return listOfMovers;
    }



    @Test
    public void whenMove4ProductsThenEachProductShouldBeMovedToCorrespondingStorage() {
        List<Food> listOfFood = generateFoodBeforeMove();
        List<Mover> listOfMovers = generateMovers();

        List<Food> warehouseList = List.of(generateFoodAfterMove().get(0));
        List<Food> shopList = List.of(generateFoodAfterMove().get(1), generateFoodAfterMove().get(2));
        List<Food> trashList = List.of(generateFoodAfterMove().get(3));

        String warehouseOutput = printTestOutput(warehouseList, "Warehouse");
        String shopOutput = printTestOutput(shopList, "Shop");
        String trashOutput = printTestOutput(trashList, "Trash");
        String expected = warehouseOutput + shopOutput + shopOutput + trashOutput;



        AdvancedQControl controlQuality = new AdvancedQControl(listOfMovers, new Resorter(listOfMovers));
        controlQuality.moveProducts(listOfFood);

        for (Mover each : listOfMovers) {
            each.getStorage().printBalance();
        }
        String actual = baos.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveProductToTheShopAndResortShouldMakeDiscountTwice() {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime milkExpire = today.plusDays(1);
        LocalDateTime milkCreateDate = today.minusDays(8);
        Food milk1 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 50, 50);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(milk1);

        Shop shop = new Shop("Shop");
        FoodMover foodMoverToShopDisc = new MoveToTheShopDisc(shop);
        List<Mover> listOfMovers = List.of(foodMoverToShopDisc);

        Food milk2 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 12.5, 50);

        List<Food> shopList = List.of(milk2);
        String shopOutput = printTestOutput(shopList, "Shop");
        String expected =  shopOutput;

        AdvancedQControl controlQuality = new AdvancedQControl(listOfMovers, new Resorter(listOfMovers));
        controlQuality.moveProducts(listOfFood);
        controlQuality.resort();
        for (Mover each : listOfMovers) {
            each.getStorage().printBalance();
        }
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }


}