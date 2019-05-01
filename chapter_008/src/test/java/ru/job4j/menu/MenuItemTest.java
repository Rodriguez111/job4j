package ru.job4j.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuItemTest {
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

    public String printTestOutput(String offset, String number, String name) {
        return offset + number + " " + name + LS;
    }

    @Test
    public void whenPrintItemThenPrintItemOfTheMenu() {
        MenuItem menuItem = new MenuItem("Menu1");
        menuItem.printItem();
        String actual = baos.toString();
        String expected = printTestOutput("", "1.", "Menu1");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSetSubMenuThenGetThisSubMenuNumber() {
        MenuItem menuItem = new MenuItem("Menu1");
        MenuItem subMenu = new MenuItem("SubMenu1");
        menuItem.setSubMenu(subMenu);

        String actual = menuItem.getSubMenu().get(0).getNumber();
        String expected = "1.1.";
        assertThat(actual, is(expected));
    }
}