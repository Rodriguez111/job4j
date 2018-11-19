package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class SquareTest {

    @Test
    public void whenBound3Then149() {
        Square square = new Square();
        assertThat(square.calculate(3), is(new int[]{1, 4, 9}));
    }

    @Test
    public void whenBound5Then1491625() {
        Square square = new Square();
        assertThat(square.calculate(5), is(new int[]{1, 4, 9, 16, 25}));
    }


    @Test
    public void whenBound0ThenEmpty() {
        Square square = new Square();
        assertThat(square.calculate(0), is(new int[0]));
    }
}
