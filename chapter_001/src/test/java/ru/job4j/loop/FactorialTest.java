package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class FactorialTest {

    @Test
    public void factorialOfFive() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(5), is(120));
    }

    @Test
    public void factorialOfZero() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(0), is(1));
    }
}


