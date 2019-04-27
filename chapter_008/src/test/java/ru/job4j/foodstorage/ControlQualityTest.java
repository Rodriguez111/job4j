package ru.job4j.foodstorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;
import ru.job4j.foodstorage.movement.*;
import ru.job4j.foodstorage.storage.Shop;
import ru.job4j.foodstorage.storage.Trash;
import ru.job4j.foodstorage.storage.Warehouse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {
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

    @Test
    public void whenMove4ProductsThenEachProductShouldBeMovedToCorrespondingStorage() {
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

        List<FoodInterface> listOfFood = new ArrayList<>();
        listOfFood.add(milk1);
        listOfFood.add(beef);
        listOfFood.add(pork);
        listOfFood.add(cheese);

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

        ControlQuality controlQuality = new ControlQuality(listOfMovers);
        controlQuality.moveProducts(listOfFood);

        warehouse.printBalance();
        shop.printBalance();
        trash.printBalance();

        List<Food> warehouseList = List.of(milk1);
        List<Food> shopList = List.of(beef, pork);
        List<Food> trashList = List.of(cheese);

        String warehouseOutput = printTestOutput(warehouseList, "Warehouse");
        String shopOutput = printTestOutput(shopList, "Shop");
        String trashOutput = printTestOutput(trashList, "Trash");

        String actual = warehouseOutput + shopOutput + trashOutput;
        String expected = baos.toString();

        assertThat(actual, is(expected));
    }
}