package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class MatrixTest {
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] actual = matrix.multiple(2);
        int[][] expected = new int[][]{{1, 2}, {2, 4}};
        assertThat(actual, is(expected));
    }

    @Test
    public void when5on5() {
        Matrix matrix = new Matrix();
        int[][] actual = matrix.multiple(5);
        int[][] expected = new int[][]{{1, 2, 3, 4, 5}, {2, 4, 6, 8, 10}, {3, 6, 9, 12, 15}, {4, 8, 12, 16, 20}, {5, 10, 15, 20, 25}};
        assertThat(actual, is(expected));
    }



}
