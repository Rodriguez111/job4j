package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max max = new Max();
       assertThat(max.max(1, 2), is(2));
    }

    @Test
    public void whenFirstLargerSecond() {
        Max max = new Max();
        assertThat(max.max(8, 3), is(8));
    }

}
