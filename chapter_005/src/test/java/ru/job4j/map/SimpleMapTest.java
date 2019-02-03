package ru.job4j.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    SimpleMap<String, Integer> simpleMap = new SimpleMap();
    String lineSeparator = System.lineSeparator();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream original;
    PrintStream newStream;

    @Before
    public void before() {
        original = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @After
    public void after() {
        System.setOut(original);
    }

    @Test
    public void shouldInsert6ElementsAndReturn3() {
        simpleMap.insert("Four", 4);
        simpleMap.insert("Five", 5);
        simpleMap.insert("Six", 6);
        simpleMap.insert("Seven", 7);
        simpleMap.insert("Eight", 8);
        simpleMap.insert("Nine", 9);

        for (SimpleMap.Entry<String, Integer> each : simpleMap) {
            System.out.println(each.key + " - " + each.value);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Seven - 7")
                .append(lineSeparator)
                .append("Four - 4")
                .append(lineSeparator)
                .append("Eight - 8")
                .append(lineSeparator);
        String expected = sb.toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));

    }

    @Test
    public void shouldInsert6ElementsAndReturn3Keys() {
        simpleMap.insert("Four", 4);
        simpleMap.insert("Five", 5);
        simpleMap.insert("Six", 6);
        simpleMap.insert("Seven", 7);
        simpleMap.insert("Eight", 8);
        simpleMap.insert("Nine", 9);

        for (String each : simpleMap.keySet()) {
            System.out.println(each);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Seven")
                .append(lineSeparator)
                .append("Four")
                .append(lineSeparator)
                .append("Eight")
                .append(lineSeparator);
        String expected = sb.toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }

}