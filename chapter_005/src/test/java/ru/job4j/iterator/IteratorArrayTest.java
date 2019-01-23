package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class IteratorArrayTest {
    private Iterator it;

    @Before
    public void setUp() {
        it = new IteratorArray(new int[][]{{1}, {3, 4}, {7}});
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void shouldReturnJaggedArraySize() {
        int[][] jaggedArray = {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        IteratorArray iteratorArray = new IteratorArray(jaggedArray);

        int actual = iteratorArray.arraySize();
        int expected = 14;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturn3() {
        int[][] jaggedArray = {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        IteratorArray iteratorArray = new IteratorArray(jaggedArray);

        iteratorArray.next();
        iteratorArray.next();
        int actual = (int) iteratorArray.next();
        int expected = 3;
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnTrue() {
        int[][] jaggedArray = {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        IteratorArray iteratorArray = new IteratorArray(jaggedArray);

        for (int i = 0; i < 13; i++) {
            iteratorArray.next();
        }

        boolean actual = iteratorArray.hasNext();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnFalse() {
        int[][] jaggedArray = {{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}};
        IteratorArray iteratorArray = new IteratorArray(jaggedArray);

        for (int i = 0; i < 14; i++) {
            iteratorArray.next();
        }

        boolean actual = iteratorArray.hasNext();
        boolean expected = false;

        assertThat(actual, is(expected));
    }

}