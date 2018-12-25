package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    PrintStream original;
    Consumer<String> output;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();


   @Before
    public void saveDefaultStream() {
        this.original = System.out;
       output  = new Consumer<String>() {
           PrintStream newPrintStream = new PrintStream(baos);
           @Override
           public void accept(String s) {

               newPrintStream.println(s);
           }
       };
    }

    @After
    public void returnDefaultStream() {
        System.setOut(original);
    }



    @Test
    public void addTest() {
        String[] answers = new String[]{"1", "Name1", "Description1", "7"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker, output);
        String actual = tracker.getAll().get(0).getName();
        assertThat(actual, is("Name1"));
    }

    @Test
    public void editTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test1", "Descr1");
        tracker.add(item);
        Item item2 = new Item("Test2", "Descr2");
        item2.setId(item.getId());
        String[] answers = new String[]{"3", item.getId(), "Test2", "Descr2", "7"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker, output);
        String actual = tracker.findById(item2.getId()).getName();
        assertThat(actual, is("Test2"));
    }

    @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr1");
        Item item2 = new Item("Test2", "Descr2");
        Item item3 = new Item("Test3", "Descr3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        String[] answers = new String[]{"4", item2.getId(), "7"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker, output);
        List<Item> actual = tracker.getAll();
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(actual, is(expected));
    }

    @Test
    public void showAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr1");
        Item item2 = new Item("Test2", "Descr2");
        Item item3 = new Item("Test3", "Descr3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String info1 = item1.getId() + " Test1 " + "Descr1";
        String info2 = item2.getId() + " Test2 " + "Descr2";
        String info3 = item3.getId() + " Test3 " + "Descr3";
        String lineSeparator = System.lineSeparator();
        String[] answers = new String[]{"2", "7"};
        Input input = new StubInput(answers);

        StartUI startUI = new StartUI(input, tracker, output);


             String expected =  new StringBuilder()
                        .append(menuEmulator())
                        .append("----- Список всех заявок: -----")
                        .append(lineSeparator)
                        .append(info1)
                        .append(lineSeparator)
                        .append(info2)
                        .append(lineSeparator)
                        .append(info3)
                        .append(lineSeparator)
                        .append("----- Конец списка -----")
                        .append(lineSeparator)
                        .append(menuEmulator())
                        .append("Program complete.")
                        .append(lineSeparator)
                        .toString();

       String actual = output.toString();
       output.accept(expected);
        assertThat(actual, is(output.toString()));
    }

    @Test
    public void findItemById() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr1");
        Item item2 = new Item("Test2", "Descr2");
        Item item3 = new Item("Test3", "Descr3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        String lineSeparator = System.lineSeparator();
        String info = item2.getId() + " Test2 " + "Descr2";
        String[] answers = new String[]{"5", item2.getId(),  "7"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker, output);
        String expected = new StringBuilder()
                .append(menuEmulator())
                .append("----- Поиск заявки по ID: -----")
                .append(lineSeparator)
                .append(info)
                .append(lineSeparator)
                .append("----- Поиск завершен -----")
                .append(lineSeparator)
                .append(menuEmulator())
                .append("Program complete.")
                .append(lineSeparator)
                .toString();
        String actual = output.toString();
        output.accept(expected);
        assertThat(actual, is(output.toString()));
    }

    @Test
    public void findItemsByName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr1");
        Item item2 = new Item("Test2", "Descr2");
        Item item3 = new Item("Test2", "Descr3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        String lineSeparator = System.lineSeparator();
        String info1 = item2.getId() + " Test2 " + "Descr2";
        String info2 = item3.getId() + " Test2 " + "Descr3";

        String[] answers = new String[] {"6", "Test2",  "7"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker, output);
        String expected = new StringBuilder()
                .append(menuEmulator())
                .append("----- Поиск заявок по названию: -----")
                .append(lineSeparator)
                .append(info1)
                .append(lineSeparator)
                .append(info2)
                .append(lineSeparator)
                .append("----- Поиск завершен -----")
                .append(lineSeparator)
                .append(menuEmulator())
                .append("Program complete.")
                .append(lineSeparator)
                .toString();
        String actual = output.toString();
        output.accept(expected);
        assertThat(actual, is(output.toString()));
    }


    private String menuEmulator() {
       StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();
       sb.append("1 Add new item")
               .append(lineSeparator)
               .append("2 Show all items")
               .append(lineSeparator)
               .append("3 Edit item by ID")
               .append(lineSeparator)
               .append("4 Delete item by ID")
               .append(lineSeparator)
               .append("5 Find item by ID")
               .append(lineSeparator)
               .append("6 Find item by name")
               .append(lineSeparator)
               .append("7 Exit program")
               .append(lineSeparator);
       return sb.toString();
    }


}
