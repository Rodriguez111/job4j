package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.Calculator;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calculator = new Calculator();
        calculator.add(1D, 1D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    public void whenSubtractFiveMinusOneThenFour() {
        Calculator calculator = new Calculator();
        calculator.subtract(5D, 1D);
        double result = calculator.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    public void whenDivSixDivideThreeThenTwo() {
        Calculator calculator = new Calculator();
        calculator.div(6D, 3D);
        double result = calculator.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    public void whenMultipleFourMultiplyFiveThenTwenty() {
        Calculator calculator = new Calculator();
        calculator.multiple(4D, 5D);
        double result = calculator.getResult();
        double expected = 20D;
        assertThat(result, is(expected));
    }
}
