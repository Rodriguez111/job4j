package ru.job4j.tracker;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        tracker.delete(item2.getId());
        List<Item> actual = tracker.findAll();
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
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        List<Item> actual = tracker.findAll();
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
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        List<Item> actual = tracker.findByName("Test2");
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
