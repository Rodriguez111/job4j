package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

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


}
