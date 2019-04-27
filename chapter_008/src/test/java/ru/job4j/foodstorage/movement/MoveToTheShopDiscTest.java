package ru.job4j.foodstorage.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.food.FoodInterface;
import ru.job4j.foodstorage.storage.Shop;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MoveToTheShopDiscTest {
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
    public void whenCurrentDayIsMoreThen75AndLessThenExpireDateThenMoveToShopAndMakeDiscount() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(1);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<FoodInterface> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Shop shop = new Shop("Shop");
        FoodMover mover = new MoveToTheShopDisc(shop);
        mover.move(listOfFood);

        shop.printBalance();

        Food foodWithDiscount = new Food("Milk", milkExpire, milkCreateDate, 80, 20);
        List<Food> testList = List.of(foodWithDiscount);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Shop");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenCurrentDayIsLessThen75PercentsThenDoNotMoveToShop() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(5);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<FoodInterface> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Shop shop = new Shop("Shop");
        FoodMover mover = new MoveToTheShopDisc(shop);
        mover.move(listOfFood);

        shop.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Shop");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenCurrentDayIsMoreThenExpireDateThenDoNotMoveToShop() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.minusDays(1);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<FoodInterface> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Shop shop = new Shop("Shop");
        FoodMover mover = new MoveToTheShopDisc(shop);
        mover.move(listOfFood);

        shop.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Shop");

        assertThat(actual, is(expected));
    }

}