package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MatrixCheckTest {
    @Test
    public void check4x4True() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = new boolean[][]{{true, false, false, true}, {false, true, true, false}, {false, true, true, false}, {true, false, false, true}};
        boolean actual = matrixCheck.mono(data);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void check4x4False() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = new boolean[][]{{true, false, false, true}, {false, false, true, false}, {false, true, true, false}, {true, false, false, true}};
        boolean actual = matrixCheck.mono(data);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void check2x2True() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = new boolean[][]{{false, false}, {false, false}};
        boolean actual = matrixCheck.mono(data);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void check2x2False() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = new boolean[][]{{true, false}, {false, false}};
        boolean actual = matrixCheck.mono(data);
        boolean expected = false;
        assertThat(actual, is(expected));
    }
}
