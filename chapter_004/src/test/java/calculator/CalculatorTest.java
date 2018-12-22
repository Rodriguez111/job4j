package calculator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class CalculatorTest {

   @Test
    public void CalculatorTest() {
        Calculator calculator = new Calculator();
        List<Double> actual = new ArrayList<>();
        calculator.multiply(0, 5, 2,
                (value, index) -> {
            return (double)value * index;
                }, result -> actual.add(result));

        List<Double> expected = new ArrayList<>();
        expected.add(0.0);
        expected.add(2.0);
        expected.add(4.0);
        expected.add(6.0);
        expected.add(8.0);

        assertThat(actual, is(expected));
    }

    @Test
    public void CalculatorTest2() {
        Calculator calculator = new Calculator();
        List<Double> actual = new ArrayList<>();
        calculator.multiply(0, 5, 2,
                MathUtil::multiply,
                actual::add);



        List<Double> expected = new ArrayList<>();
        expected.add(0.0);
        expected.add(2.0);
        expected.add(4.0);
        expected.add(6.0);
        expected.add(8.0);

        assertThat(actual, is(expected));
    }

}
