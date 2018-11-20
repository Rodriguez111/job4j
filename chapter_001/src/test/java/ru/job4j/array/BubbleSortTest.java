package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void sortingArray() {
     BubbleSort bubbleSort = new BubbleSort();
        int[] actual = bubbleSort.sort(new int[]{5, 8, 2, 4, 1, -2, 25});
        int[] expected = new int[]{-2, 1, 2, 4, 5, 8, 25};
        assertThat(actual, is(expected));

    }


}
