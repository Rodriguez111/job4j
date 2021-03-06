package ru.job4j.calculator;

import org.junit.Test;
import ru.job4j.calculator.Calculate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Rodriguez (mymail4java@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class CalculateTest {

    /**
     * Test echo.
     */
    @Test

    public void whenTakeNameThenTreeEchoPlusName() {
        String input = "Rodriguez";
        String expect = "Echo, echo, echo : Rodriguez";
        Calculate calc = new Calculate();
        String result = calc.echo(input);
        assertThat(result, is(expect));
    }



}