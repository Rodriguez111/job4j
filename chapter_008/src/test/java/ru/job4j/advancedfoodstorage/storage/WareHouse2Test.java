package ru.job4j.advancedfoodstorage.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.advancedfoodstorage.food.FoodType;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.movement.FoodMover;
import ru.job4j.foodstorage.movement.MoveToTheWareHouse;
import ru.job4j.foodstorage.storage.Warehouse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WareHouse2Test {
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
    public void whenCurrentDayIsLessThen25PercentsThenMoveWareHouse2() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Warehouse warehouse2 = new WareHouse2("Warehouse2");
        FoodMover mover = new MoveToTheWareHouse(warehouse2);
        mover.move(listOfFood);

        warehouse2.printBalance();

        List<Food> testList = List.of(food);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Warehouse2");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenCurrentDayIsLessThen25PercentsAndIsAdvancedProductNotVegetableThenMoveWareHouse2() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food food = new AdvancedFood("AdvancedMilk", milkExpire, milkCreateDate,
                100, 20, false, FoodType.MILK);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Warehouse warehouse2 = new WareHouse2("Warehouse2");
        FoodMover mover = new MoveToTheWareHouse(warehouse2);
        mover.move(listOfFood);

        warehouse2.printBalance();

        List<Food> testList = List.of(food);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Warehouse2");

        assertThat(actual, is(expected));
    }

}