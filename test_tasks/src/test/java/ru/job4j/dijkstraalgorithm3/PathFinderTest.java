package ru.job4j.dijkstraalgorithm3;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTest {
    @Test
    public void whenFindShortestPathThenReturnResult4AndPath11115() {
        PathFinder pathFinder = new PathFinder();
        int[][] board = {{1, 2, 3}, {1, 3, 6}, {1, 1, 5}};
        int actual = pathFinder.optimalWay(board, 0, 0, 2, 2);
        int expected = 4;
        Assert.assertThat(actual, is(expected));
    }

    @Test
    public void whenFindShortestPathThenReturnResult9AndPath11113754() {
        PathFinder pathFinder = new PathFinder();
        int[][] board = {{1, 2, 3, 8, 16}, {1, 3, 6, 4, 1}, {1, 1, 5, 9, 2}, {8, 3, 7, 5, 4}};
        int actual = pathFinder.optimalWay(board, 0, 0, 4, 3);
        int expected = 9;

        Assert.assertThat(actual, is(expected));

    }

}