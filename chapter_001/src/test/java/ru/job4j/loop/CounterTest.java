package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class CounterTest {

    @Test
    public void sumOfEvenNumbersFromOneToTen() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 10), is(30));
    }

}
