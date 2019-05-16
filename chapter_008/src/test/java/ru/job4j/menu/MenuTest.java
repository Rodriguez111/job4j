package ru.job4j.menu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.AfterEach;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    private final static String DELIMITER = "=======================";
    private final static String LS = System.lineSeparator();

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

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


    }

    public String printTestMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(DELIMITER)
                .append(LS)

                .append("1. First")
                .append(LS)
                .append(" 1.1. SubItem")
                .append(LS)
                .append(" 1.2. Another subItem")
                .append(LS)
                .append(" 1.3. One more subItem")
                .append(LS)
                .append("  1.3.1. Sub subItem")
                .append(LS)
                .append("  1.3.2. Another sub subItem")
                .append(LS)
                .append(" 1.4. Just one more else subItem")
                .append(LS)
                .append("  1.4.1. This is sub subItem too")
                .append(LS)
                .append("2. Second")
                .append(LS)
                .append("3. Third")
                .append(LS)
                .append("4. Exit")
                .append(LS)
                .append(DELIMITER);
        return sb.toString();
    }

    @Test
    public void whenSelectItemThenPrintMessageAccordingToThisItem() {
        List<String> list = List.of("1.", "4.");
        Menu menu = new Menu(new FakeInput(list));

        String actual = baos.toString();
        String expected = printTestMenu() + LS + "Menu item is running..." + LS + printTestMenu() + LS + "Bye-bye" + LS;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSelectItemWhichNotExistsThenPrintIncorrectItemMessage() {
        List<String> list = List.of("5.", "4.");
        Menu menu = new Menu(new FakeInput(list));

        String actual = baos.toString();
        String expected = printTestMenu() + LS + "Incorrect item number, please try again" + LS + printTestMenu() + LS + "Bye-bye" + LS;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSelectItemWhichDoNotHaveAccordingActionThenPrintNoActionMessage() {
        List<String> list = List.of("3.", "4.");
        Menu menu = new Menu(new FakeInput(list));

        String actual = baos.toString();
        String expected = printTestMenu() + LS + "No action is associated with this item." + LS + printTestMenu() + LS + "Bye-bye" + LS;
        assertThat(actual, is(expected));
    }

}