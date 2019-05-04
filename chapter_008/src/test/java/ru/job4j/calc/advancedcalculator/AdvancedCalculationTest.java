package ru.job4j.calc.advancedcalculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdvancedCalculationTest {
    @Test
    public void whenExtractRoot2From81Then9() {
        AdvancedCalculation advancedCalculation = new AdvancedCalculation();
        advancedCalculation.root(81, 2);

        double actual = advancedCalculation.getResult();
        double expected = 9.000000000000002;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenExp2ToPower3Then8() {
        AdvancedCalculation advancedCalculation = new AdvancedCalculation();
        advancedCalculation.exp(2, 3);

        double actual = advancedCalculation.getResult();
        double expected = 8.0;
        assertThat(actual, is(expected));
    }

}