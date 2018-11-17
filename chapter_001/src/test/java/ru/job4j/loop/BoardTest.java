package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void when3x3() {
        Board board = new Board();
       String result = board.paint(3, 3);
       String ln = System.lineSeparator();
       assertThat(result, is(String.format("X X%s X %sX X%s", ln, ln, ln)));
    }
}
