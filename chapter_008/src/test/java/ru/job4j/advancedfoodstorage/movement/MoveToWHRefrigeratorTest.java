package ru.job4j.advancedfoodstorage.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.advancedfoodstorage.food.AdvancedFood;
import ru.job4j.advancedfoodstorage.food.FoodType;
import ru.job4j.advancedfoodstorage.storage.Recycle;
import ru.job4j.advancedfoodstorage.storage.RefrigeratorWareHouse;
import ru.job4j.foodstorage.food.Food;
import ru.job4j.foodstorage.movement.MoveToTheTrash;
import ru.job4j.foodstorage.movement.MoveToTheWareHouse;
import ru.job4j.foodstorage.movement.Mover;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoveToWHRefrigeratorTest {
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
    public void whenAdvancedProductIsVegetableThenMoveToRefrigerator() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomatoExpire = today.plusDays(10);
        LocalDateTime tomatoCreateDate = today.minusDays(1);
        Food food = new AdvancedFood("AdvancedTomato", tomatoExpire,
                tomatoCreateDate, 100, 20,
                true, FoodType.VEGETABLES);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        RefrigeratorWareHouse refrigeratorWareHouse = new RefrigeratorWareHouse("RefrigeratorWareHouse");
        Mover mover = new MoveToWHRefrigerator(new MoveToTheWareHouse(refrigeratorWareHouse));
        mover.move(listOfFood);

        refrigeratorWareHouse.printBalance();

        List<Food> testList = List.of(food);
        String actual = baos.toString();
        String expected = printTestOutput(testList, "RefrigeratorWareHouse");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdvancedProductIsNotVegetableThenDoNotMoveToRefrigerator() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime bananaExpire = today.plusDays(10);
        LocalDateTime bananaCreateDate = today.minusDays(1);
        Food food = new AdvancedFood("AdvancedBanana", bananaExpire,
                bananaCreateDate, 100, 20,
                true, FoodType.FRUITS);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        RefrigeratorWareHouse refrigeratorWareHouse = new RefrigeratorWareHouse("RefrigeratorWareHouse");
        Mover mover = new MoveToWHRefrigerator(new MoveToTheWareHouse(refrigeratorWareHouse));
        mover.move(listOfFood);

        refrigeratorWareHouse.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "RefrigeratorWareHouse");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdvancedProductIsVegetableButIsForShopThenDoNotMoveToRefrigerator() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime bananaExpire = today.plusDays(5);
        LocalDateTime bananaCreateDate = today.minusDays(5);
        Food food = new AdvancedFood("AdvancedBanana", bananaExpire,
                bananaCreateDate, 100, 20,
                true, FoodType.VEGETABLES);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        RefrigeratorWareHouse refrigeratorWareHouse = new RefrigeratorWareHouse("RefrigeratorWareHouse");
        Mover mover = new MoveToWHRefrigerator(new MoveToTheWareHouse(refrigeratorWareHouse));
        mover.move(listOfFood);

        refrigeratorWareHouse.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "RefrigeratorWareHouse");

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSimpleProductThenDoNotMoveToRefrigerator() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime bananaExpire = today.plusDays(10);
        LocalDateTime bananaCreateDate = today.minusDays(1);
        Food food = new Food("SimpleBanana", bananaExpire,
                bananaCreateDate, 100, 20);
        List<Food> listOfFood = new ArrayList<>();
        listOfFood.add(food);

        RefrigeratorWareHouse refrigeratorWareHouse = new RefrigeratorWareHouse("RefrigeratorWareHouse");
        Mover mover = new MoveToWHRefrigerator(new MoveToTheWareHouse(refrigeratorWareHouse));
        mover.move(listOfFood);

        refrigeratorWareHouse.printBalance();

        List<Food> testList = List.of();
        String actual = baos.toString();
        String expected = printTestOutput(testList, "RefrigeratorWareHouse");

        assertThat(actual, is(expected));
    }


}