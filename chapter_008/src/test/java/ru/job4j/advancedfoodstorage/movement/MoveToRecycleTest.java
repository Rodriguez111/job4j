package ru.job4j.advancedfoodstorage.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.advancedfoodstorage.food.FoodType;
import ru.job4j.advancedfoodstorage.storage.Recycle;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.movement.MoveToTheTrash;
import ru.job4j.foodstorage.movement.Mover;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class MoveToRecycleTest {
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
    public void whenAdvancedProductIsExpiredAndIsRecyclableThenMoveRecycle() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.minusDays(2);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new AdvancedFood("AdvancedMilk", milkExpire,
                milkCreateDate, 100, 20,
                true, FoodType.MILK);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Recycle recycle = new Recycle("Recycle");
        Mover mover = new MoveToRecycle(new MoveToTheTrash(recycle));
        mover.move(listOfFood);

        recycle.printBalance();

        List<Food> testList = List.of(food);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Recycle");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdvancedProductIsExpiredAndIsNotRecyclableThenDoNotMoveRecycle() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.minusDays(2);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new AdvancedFood("AdvancedMilk", milkExpire,
                milkCreateDate, 100, 20,
                false, FoodType.MILK);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Recycle recycle = new Recycle("Recycle");
        Mover mover = new MoveToRecycle(new MoveToTheTrash(recycle));
        mover.move(listOfFood);

        recycle.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Recycle");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdvancedProductIsNotExpiredAndIsRecyclableThenDoNotMoveRecycle() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.plusDays(2);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new AdvancedFood("AdvancedMilk", milkExpire,
                milkCreateDate, 100, 20,
                true, FoodType.MILK);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Recycle recycle = new Recycle("Recycle");
        Mover mover = new MoveToRecycle(new MoveToTheTrash(recycle));
        mover.move(listOfFood);

        recycle.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Recycle");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSimpleProductIsExpiredThenDoNotMoveRecycle() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime milkExpire = today.minusDays(2);
        LocalDateTime milkCreateDate = today.minusDays(10);
        Food food = new Food("SimpleMilk", milkExpire,
                milkCreateDate, 100, 20);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        Recycle recycle = new Recycle("Recycle");
        Mover mover = new MoveToRecycle(new MoveToTheTrash(recycle));
        mover.move(listOfFood);

        recycle.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "Recycle");

        assertThat(actual, is(expected));
    }


}