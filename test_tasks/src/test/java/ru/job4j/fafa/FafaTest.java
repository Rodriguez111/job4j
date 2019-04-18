package ru.job4j.fafa;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class FafaTest {
    public final Fafa fafa = new Fafa();

    @Test
    public void when10employeesThen3Variants() {
        int actual = fafa.calculateVariants(10);
        int expected = 3;
        assertThat(actual, is(expected));
    }

    @Test
    public void when2employeesThen1Variants() {
        int actual = fafa.calculateVariants(2);
        int expected = 1;
        assertThat(actual, is(expected));
    }

    @Test
    public void when0employeesThen0Variants() {
        int actual = fafa.calculateVariants(0);
        int expected = 0;
        assertThat(actual, is(expected));
    }


}