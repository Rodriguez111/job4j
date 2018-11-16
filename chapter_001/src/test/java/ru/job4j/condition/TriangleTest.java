package ru.job4j.condition;

/**
 * Tests for Triangle
 */

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class TriangleTest {

    @Test
    public void semiPerimeterTest() {
        Point a = new Point(2, 3);
        Point b = new Point(8, 10);
        Point c = new Point(-6, 4);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        System.out.println(ab);
        System.out.println(ac);
        System.out.println(bc);
        System.out.println(triangle.semiPerimeter(ab, ac, bc));
    }

    @Test
    public void triangleExists() {
        Point a = new Point(2, 3);
        Point b = new Point(8, 10);
        Point c = new Point(-6, 4);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        assertThat(triangle.exists(ab, ac, bc), is(true));
    }

    @Test
    public void triangleIsNotExists() {
        Point a = new Point(2, 3);
        Point b = new Point(2, 10);
        Point c = new Point(2, 4);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        assertThat(triangle.exists(ab, ac, bc), is(false));
    }

    @Test
    public void triangleAreaWhenExists() {
        Point a = new Point(2, 3);
        Point b = new Point(8, 10);
        Point c = new Point(-6, 4);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        assertThat(triangle.area(), closeTo(31.0, 0.1));
    }

    @Test
    public void triangleAreaWhenNotExists() {
        Point a = new Point(2, 3);
        Point b = new Point(2, 10);
        Point c = new Point(2, 4);
        Triangle triangle = new Triangle(a, b, c);
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        assertThat(triangle.area(), is(-1.0));
    }



}
