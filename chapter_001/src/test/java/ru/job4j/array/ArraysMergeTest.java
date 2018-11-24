package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArraysMergeTest {


    @Test
    public void array1SizeLessArray2Size() {
    int[] array1 = {0, 2, 9, 14, 18, 25};
    int[] array2 = {-5, 1, 10, 11, 17, 21, 22, 28, 41};
    ArraysMerge arraysMerge = new ArraysMerge();
        int[] actual = arraysMerge.arraysMerging(array1, array2);
        int[] expected = {-5, 0, 1, 2, 9, 10, 11, 14, 17, 18, 21, 22, 25, 28, 41};
        assertThat(actual, is(expected));
    }

    @Test
    public void array1SizeLargerArray2Size() {
        int[] array1 = {0, 2, 9, 14, 18, 25};
        int[] array2 = {-5, 1, 10, 11};
        ArraysMerge arraysMerge = new ArraysMerge();
        int[] actual = arraysMerge.arraysMerging(array1, array2);
        int[] expected = {-5, 0, 1, 2, 9, 10, 11, 14, 18, 25};
        assertThat(actual, is(expected));
    }

    @Test
    public void array1SizeEqualsArray2Size() {
        int[] array1 = {0, 2, 9, 14, 18, 25};
        int[] array2 = {-5, 1, 10, 11, 17, 21};
        ArraysMerge arraysMerge = new ArraysMerge();
        int[] actual = arraysMerge.arraysMerging(array1, array2);
        int[] expected = {-5, 0, 1, 2, 9, 10, 11, 14, 17,  18, 21, 25};
        assertThat(actual, is(expected));
    }




}
