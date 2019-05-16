package ru.job4j.menu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RootItemTest {
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

    public String printTestItem(String offset, String number, String name) {
        return offset + number + " " + name + LS;
    }

    public String printTestMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(DELIMITER)
                .append(LS)
                .append(printTestItem("", "1.", "Menu1"))
                .append(printTestItem(" ", "1.1.", "SubMenu1"))
                .append(printTestItem("  ", "1.1.1.", "SubSubMenu1"))
                .append(DELIMITER)
                .append(LS);
        return sb.toString();
    }

    @Test
    public void whenPrintMenuThenPrintAllItemsWithAllSubItems() {
        MenuItem menuItem = new MenuItem("Menu1");
        MenuItem subMenu = new MenuItem("SubMenu1");
        MenuItem subSubMenu = new MenuItem("SubSubMenu1");
        menuItem.setSubMenu(subMenu);
        subMenu.setSubMenu(subSubMenu);

        RootItem rootItem = new RootItem();
        rootItem.addItem("0.", menuItem);
        rootItem.printMenu();

        String actual = baos.toString();
        String expected = printTestMenu();
        assertThat(actual, is(expected));
    }
}