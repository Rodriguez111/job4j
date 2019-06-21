package ru.job4j.dijkstraalgorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.Matchers.is;
public class PathFinderTest {
    @Test
    public void whenFindShortestPathThenReturnResult4AndPath11115() {
        PathFinder pathFinder = new PathFinder();
        int[][] board = {{1, 2, 3}, {1, 3, 6}, {1, 1, 5}};
        int actual = pathFinder.optimalWay(board, 0, 0, 2, 2);
        int expected = 4;

        List<Integer> actualList = pathFinder.pickPath(0, 0, 2, 2);
        List<Integer> expectedList = List.of(1, 1, 1, 1, 5);
        Assert.assertThat(actual, is(expected));
        Assert.assertThat(actualList, is(expectedList));
    }

    @Test
    public void whenFindShortestPathThenReturnResult9AndPath11113754() {
        PathFinder pathFinder = new PathFinder();
        int[][] board = {{1, 2, 3, 8, 16}, {1, 3, 6, 4, 1}, {1, 1, 5, 9, 2}, {8, 3, 7, 5, 4}};
        int actual = pathFinder.optimalWay(board, 0, 0, 4, 3);
        int expected = 9;

        List<Integer> actualList = pathFinder.pickPath(0, 0, 4, 3);
        List<Integer> expectedList = List.of(1, 1, 1, 1, 3, 7, 5, 4);
        Assert.assertThat(actual, is(expected));
        Assert.assertThat(actualList, is(expectedList));
    }
}