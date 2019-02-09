package ru.job4j.statistic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    private   Analyze analyze = new Analyze();
    private  Analyze.User user1 = new Analyze.User(1, "Ivan");
    private Analyze.User user2 = new Analyze.User(2, "Vasya");
    private  Analyze.User user3 = new Analyze.User(3, "Petya");
    private Analyze.User user4 = new Analyze.User(4, "Kolya");
    private Analyze.User user5 = new Analyze.User(5, "Sasha");
    private Analyze.User user6 = new Analyze.User(3, "Anton");


    @Test
    public void shouldReturn1Changed() {
        List<Analyze.User> list1 = new ArrayList<>();
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);
        list1.add(user4);
        list1.add(user5);

        List<Analyze.User> list2 = new ArrayList<>();
        list2.add(user1);
        list2.add(user2);
        list2.add(user6);
        list2.add(user4);
        list2.add(user5);

        Analyze.Info actual = analyze.diff(list1, list2);
        Analyze.Info expected = new Analyze.Info();
        expected.changed = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturn1Added1Deleted() {
        List<Analyze.User> list1 = new ArrayList<>();
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);
        list1.add(user4);

        List<Analyze.User> list2 = new ArrayList<>();
        list2.add(user2);
        list2.add(user3);
        list2.add(user4);
        list2.add(user5);

        Analyze.Info actual = analyze.diff(list1, list2);
        Analyze.Info expected = new Analyze.Info();
        expected.added = 1;
        expected.deleted = 1;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturn3Added() {
        List<Analyze.User> list1 = new ArrayList<>();
        list1.add(user1);

        List<Analyze.User> list2 = new ArrayList<>();
        list2.add(user1);
        list2.add(user2);
        list2.add(user3);
        list2.add(user4);

        Analyze.Info actual = analyze.diff(list1, list2);
        Analyze.Info expected = new Analyze.Info();
        expected.added = 3;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturn1Changed2Deleted() {
        List<Analyze.User> list1 = new ArrayList<>();
        list1.add(user1);
        list1.add(user2);
        list1.add(user3);

        List<Analyze.User> list2 = new ArrayList<>();
        list2.add(user6);

        Analyze.Info actual = analyze.diff(list1, list2);
        Analyze.Info expected = new Analyze.Info();
        expected.changed = 1;
        expected.deleted = 2;
        assertThat(actual, is(expected));
    }



}