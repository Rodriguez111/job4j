package functions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class FunctionsTest {

    @Test
    public void whenLinearThen0246() {
        Functions functions = new Functions();
        List<Double> actual =   functions.diapason(0, 3, (value) -> {
            double coefficient = 2;
            double  result = coefficient * value;
            return result;
        });
        List<Double> expected = Arrays.asList(0.0, 2.0, 4.0, 6.0);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenQuadraticThen0246() {
        Functions functions = new Functions();
        List<Double> actual =   functions.diapason(0, 3, (value) -> {
            return value * value;
        });
        List<Double> expected = Arrays.asList(0.0, 1.0, 4.0, 9.0);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenLogarithmThenNull00610() {
        Functions functions = new Functions();
        List<Double> actual =   functions.diapason(0, 3, (value) -> {
            Double  result = null;
            if (value > 0) {
                result = Math.log(value);
            }
            return result;
        });
        List<Double> expected = Arrays.asList(null, 0.0, 0.6931471805599453, 1.0986122886681098);
        assertThat(actual, is(expected));
    }

}
