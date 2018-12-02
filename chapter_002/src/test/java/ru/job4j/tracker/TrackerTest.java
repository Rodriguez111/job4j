package ru.job4j.tracker;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import tracker.Item;
import tracker.Tracker;

public class TrackerTest {

    @Test
    public void whenAddItemReturnsSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test1", "Descr1");
        assertThat(tracker.add(item), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test1", "Descr1");
        tracker.add(item);
        Item item2 = new Item("Test2", "Descr2");
        item2.setId(item.getId());
        tracker.replace(item.getId(), item2);
        String actual = tracker.findById(item2.getId()).getName();
        assertThat(actual, is("Test2"));
    }

    @Test
    public void whenDeleteOneOfThreeRemainsTwo() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr1");
        Item item2 = new Item("Test2", "Descr2");
        Item item3 = new Item("Test3", "Descr3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = new Item[2];
        expected[0] = item1;
        expected[1] = item3;
        tracker.delete(item2.getId());
        Item[] actual = tracker.getAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenGetAllThenReturnsThree() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr");
        Item item2 = new Item("Test2", "Descr");
        Item item3 = new Item("Test3", "Descr");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = new Item[3];
        expected[0] = item1;
        expected[1] = item2;
        expected[2] = item3;
        Item[] actual = tracker.getAll();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSearchNameTest2ThenFindNameTest2() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr");
        Item item2 = new Item("Test2", "Descr");
        Item item3 = new Item("Test2", "Descr");
        Item item4 = new Item("Test3", "Descr");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        Item[] expected = new Item[2];
        expected[0] = item2;
        expected[1] = item3;
        Item[] actual = tracker.findByName("Test2");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSearchByIDFindItemWithThisID() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Test1", "Descr");
        Item item2 = new Item("Test2", "Descr");
        Item item3 = new Item("Test3", "Descr");
        Item item4 = new Item("Test4", "Descr");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        String testID = item3.getId();
        String expected = "Test3";
        String actual = tracker.findById(testID).getName();
        assertThat(actual, is(expected));
    }


}
