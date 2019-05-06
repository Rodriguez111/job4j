package ru.job4j.food.foodstorage.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.food.foodstorage.food.Food;
import ru.job4j.food.foodstorage.storage.Warehouse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoveToTheWareHouseTest {
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
    public void whenCurrentDayIsLessThen25PercentsThenMoveWareHouse() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(10);
        LocalDateTime milkCreateDate = today.minusDays(2);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Warehouse warehouse = new Warehouse("Warehouse");
        FoodMover mover = new MoveToTheWareHouse(warehouse);
        mover.move(listOfFood);

        warehouse.printBalance();

        List<Food> testList = List.of(food);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Warehouse");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenCurrentDayIsMoreThen25PercentsThenDoNotMoveToWareHouse() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(2);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new Food("Milk", milkExpire, milkCreateDate, 100, 20);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Warehouse warehouse = new Warehouse("Warehouse");
        FoodMover mover = new MoveToTheWareHouse(warehouse);
        mover.move(listOfFood);

        warehouse.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Warehouse");

        assertThat(actual, is(expected));
    }

}