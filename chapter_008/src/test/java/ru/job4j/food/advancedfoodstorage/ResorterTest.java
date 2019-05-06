package ru.job4j.food.advancedfoodstorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.food.advancedfoodstorage.movement.MoveToRecycle;
import ru.job4j.food.advancedfoodstorage.storage.Recycle;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.movement.MoveToTheShop;
import ru.job4j.food.foodstorage.movement.MoveToTheTrash;
import ru.job4j.food.foodstorage.movement.Mover;
import ru.job4j.food.foodstorage.storage.Shop;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResorterTest {
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

    public List<Food> generateFood1() {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food milk1 = new Food("Milk 2.5%", milkExpire, milkCreateDate, 20, 50);

        LocalDateTime beefExpire = today.plusDays(7);
        LocalDateTime beefCreateDate = today.minusDays(7);
        Food beef = new Food("Beef", beefExpire, beefCreateDate, 50, 30);

        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(milk1);
        listOfFood.add(beef);
        return listOfFood;
    }

    public List<Food> generateFood2() {
        LocalDateTime today = LocalDateTime.now();

        LocalDateTime porkExpire = today.plusDays(10);
        LocalDateTime porkCreateDate = today.minusDays(10);
        Food pork = new Food("Pork", porkExpire, porkCreateDate, 29.25, 35);

        LocalDateTime cheeseExpire = today.plusDays(5);
        LocalDateTime cheeseCreateDate = today.minusDays(5);
        Food cheese = new Food("Cheddar", cheeseExpire, cheeseCreateDate, 52, 50);

        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(pork);
        listOfFood.add(cheese);
        return listOfFood;
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
    public void whenGatherFoodThenPrintEmptyStorageBalance() {
        Shop shop = new Shop("Shop");
        MoveToTheShop moveToTheShop = new MoveToTheShop(shop);


        shop.addFood(generateFood1());

        List<Mover> movers = List.of(moveToTheShop);

        Resortable resorter = new Resorter(movers);
        resorter.gather();

        moveToTheShop.getStorage().printBalance();

        String expected = printTestOutput(List.of(), "Shop");
        String actual = baos.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenResortFoodThenPrintStorageBalance() {
        Shop shop = new Shop("Shop");
        MoveToTheShop moveToTheShop = new MoveToTheShop(shop);


        shop.addFood(generateFood2());

        List<Mover> movers = List.of(moveToTheShop);

        Resortable resorter = new Resorter(movers);
        resorter.resort();

        moveToTheShop.getStorage().printBalance();

        String expected = printTestOutput(generateFood2(), "Shop");
        String actual = baos.toString();

        assertThat(actual, is(expected));
    }




}