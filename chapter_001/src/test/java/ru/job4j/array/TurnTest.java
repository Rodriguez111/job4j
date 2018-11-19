package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class TurnTest {

    @Test
    public void whenTheNumberOfElementsIsEven() {
        Turn turn = new Turn();
      int[] actual = turn.back(new int[]{1, 6, 8, 2, 4, 7});
      int[] expected = new int[]{7, 4, 2, 8, 6, 1};
      assertThat(actual, is(expected));
    }

    @Test
    public void whenTheNumberOfElementsIsOdd() {
        Turn turn = new Turn();
        int[] actual = turn.back(new int[]{1, 6, 8, 2, 4});
        int[] expected = new int[]{4, 2, 8, 6, 1};
        assertThat(actual, is(expected));
    }

}
