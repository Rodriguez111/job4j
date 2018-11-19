package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void zeroElementFound() {
        FindLoop findLoop = new FindLoop();
       int actual = findLoop.indexOf(new int[]{8, 16, 25}, 8);
       int expected = 0;
       assertThat(actual, is(expected));
    }

    @Test
    public void elementNotFound() {
        FindLoop findLoop = new FindLoop();
        int actual = findLoop.indexOf(new int[]{8, 16, 25}, 9);
        int expected = -1;
        assertThat(actual, is(expected));
    }



}
