package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    PrintStream original;
    PrintStream newPrintStream;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

   @Before
    public void saveDefaultStream() {
        this.original = System.out;
       this.newPrintStream = new PrintStream(baos);
       System.setOut(newPrintStream);
    }

    @After
    public void returnDefaultStream() {
        System.setOut(original);
    }



    @Test
    public void addTest() {
        String[] answers = new String[]{"0", "Name1", "Description1", "6"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        String actual = tracker.getAll()[0].getName();
        assertThat(actual, is("Name1"));
    }

    @Test
    public void editTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test1", "Descr1");
        tracker.add(item);
        Item item2 = new Item("Test2", "Descr2");
        item2.setId(item.getId());
        String[] answers = new String[]{"2", item.getId(), "Test2", "Descr2", "6"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
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

        String[] answers = new String[]{"3", item2.getId(), "6"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        Item[] actual = tracker.getAll();
        Item[] expected = new Item[2];
        expected[0] = item1;
        expected[1] = item3;
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
        String[] answers = new String[]{"1", "6"};
        Input input = new StubInput(answers);

        StartUI startUI = new StartUI(input, tracker);

        String expected = new StringBuilder()
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
                .append("Программа завершена.")
                .append(lineSeparator)
                .toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
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
        String[] answers = new String[]{"4", item2.getId(),  "6"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
        String expected = new StringBuilder()
                .append(menuEmulator())
                .append("----- Поиск заявки по ID: -----")
                .append(lineSeparator)
                .append(info)
                .append(lineSeparator)
                .append("----- Поиск завершен -----")
                .append(lineSeparator)
                .append(menuEmulator())
                .append("Программа завершена.")
                .append(lineSeparator)
                .toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
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

        String[] answers = new String[] {"5", "Test2",  "6"};
        Input input = new StubInput(answers);
        StartUI startUI = new StartUI(input, tracker);
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
                .append("Программа завершена.")
                .append(lineSeparator)
                .toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }


    private String menuEmulator() {
       StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();
       sb.append("0 - Add new Item")
               .append(lineSeparator)
               .append("1 - Show all items")
               .append(lineSeparator)
               .append("2 - Edit item")
               .append(lineSeparator)
               .append("3 - Delete item")
               .append(lineSeparator)
               .append("4 - Find item by Id")
               .append(lineSeparator)
               .append("5 - Find items by name")
               .append(lineSeparator)
               .append("6 - ExitProgram Program")
               .append(lineSeparator);
       return sb.toString();
    }


}
