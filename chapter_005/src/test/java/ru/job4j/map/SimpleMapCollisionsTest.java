package ru.job4j.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapCollisionsTest {
    SimpleMapCollisions<String, Integer> simpleMapCollisions = new SimpleMapCollisions();
    String lineSeparator = System.lineSeparator();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream original;
    PrintStream newStream;

    @Before
    public void before() {
        original = System.out;
        newStream = new PrintStream(baos);
       // System.setOut(newStream);
    }

    @After
    public void after() {
        System.setOut(original);
    }


    @Test
    public void shouldInsertAndReturnAllElements() {
        simpleMapCollisions.insert("One", 1);
        simpleMapCollisions.insert("Two", 2);
        simpleMapCollisions.insert("Three", 3);
        simpleMapCollisions.insert("Four", 4);
        simpleMapCollisions.insert("Five", 5);
        simpleMapCollisions.insert("Six", 6);
        simpleMapCollisions.insert("Seven", 7);
        simpleMapCollisions.insert("Eight", 8);
        simpleMapCollisions.insert("Nine", 9);
        simpleMapCollisions.insert("Ten", 10);
        simpleMapCollisions.insert("Eleven", 11);
        simpleMapCollisions.insert("Twelve", 12);

        for (SimpleMapCollisions.Entry<String, Integer> each : simpleMapCollisions) {
            System.out.println(each.key + " - " + each.value);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Twelve - 12")
                .append(lineSeparator)
                .append("Seven - 7")
                .append(lineSeparator)
                .append("Ten - 10")
                .append(lineSeparator)
                .append("Eight - 8")
                .append(lineSeparator)
                .append("Eleven - 11")
                .append(lineSeparator)
                .append("Nine - 9")
                .append(lineSeparator)
                .append("Four - 4")
                .append(lineSeparator)
                .append("One - 1")
                .append(lineSeparator)
                .append("Three - 3")
                .append(lineSeparator)
                .append("Two - 2")
                .append(lineSeparator)
                .append("Six - 6")
                .append(lineSeparator)
                .append("Five - 5")
                .append(lineSeparator);
        String expected = sb.toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldInsertAllElementsAndReturnAllKeys() {
        simpleMapCollisions.insert("One", 1);
        simpleMapCollisions.insert("Two", 2);
        simpleMapCollisions.insert("Three", 3);
        simpleMapCollisions.insert("Four", 4);
        simpleMapCollisions.insert("Five", 5);
        simpleMapCollisions.insert("Six", 6);
        simpleMapCollisions.insert("Seven", 7);
        simpleMapCollisions.insert("Eight", 8);
        simpleMapCollisions.insert("Nine", 9);
        simpleMapCollisions.insert("Ten", 10);
        simpleMapCollisions.insert("Eleven", 11);
        simpleMapCollisions.insert("Twelve", 12);

        for (String each : simpleMapCollisions.keySet()) {
            System.out.println(each);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Twelve")
                .append(lineSeparator)
                .append("Seven")
                .append(lineSeparator)
                .append("Ten")
                .append(lineSeparator)
                .append("Eight")
                .append(lineSeparator)
                .append("Eleven")
                .append(lineSeparator)
                .append("Nine")
                .append(lineSeparator)
                .append("Four")
                .append(lineSeparator)
                .append("One")
                .append(lineSeparator)
                .append("Three")
                .append(lineSeparator)
                .append("Two")
                .append(lineSeparator)
                .append("Six")
                .append(lineSeparator)
                .append("Five")
                .append(lineSeparator);
        String expected = sb.toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));

    }

}