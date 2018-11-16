package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for Point
 */

public class PointTest {

    @Test
    public void distanceFromAToB() {
        Point a = new Point(8, 10);
        Point b = new Point(-4, 2);
        assertThat(a.distanceTo(b), closeTo(14.42, 0.01));
    }



}
