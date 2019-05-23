package ru.job4j.ticktacktoe.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.ticktacktoe.gamefield.Field;
import ru.job4j.ticktacktoe.gamefield.TestField;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SidesCheckTest {
    private SidesChecker sidesChecker;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream originalStream;
    private PrintStream newStream;

    @Before
    public void before() {
        originalStream = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @After
    public void after() {
        System.setOut(originalStream);
    }

    public void init(char[][] test) {
        Field testField = new TestField(test);
        this.sidesChecker = new SidesCheck(testField.getField());
    }


    @Test
    public void whenFirstHorizontalHaveALLXThenTrue() {
        char[][] test = {{'X', 'X', 'X'}, {'X', '.', 'X'}, {'.', 'O', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondHorizontalHaveALLXThenTrue() {
        char[][] test = {{'X', '.', 'X'}, {'X', 'X', 'X'}, {'.', 'O', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenThirdHorizontalHaveALLXThenTrue() {
        char[][] test = {{'X', '.', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHorizontalDontHaveALLXThenFalse() {
        char[][] test = {{'O', 'O', '.'}, {'X', '.', 'X'}, {'.', 'O', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenFirstVerticalHaveALLXThenTrue() {
        char[][] test = {{'X', 'X', '.'}, {'X', '.', 'X'}, {'X', 'X', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondVerticalHaveALLXThenTrue() {
        char[][] test = {{'.', 'X', '.'}, {'O', 'X', 'X'}, {'.', 'X', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenThirdVerticalHaveALLXThenTrue() {
        char[][] test = {{'O', 'X', 'X'}, {'X', '.', 'X'}, {'.', 'X', 'X'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenVerticalDontHaveALLXThenFalse() {
        char[][] test = {{'O', 'O', '.'}, {'X', '.', 'X'}, {'.', 'O', '.'}};
        init(test);
        boolean actual = sidesChecker.checkSides('X');
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHorizontalXThenXWon() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'X', 'X'}, {'O', 'O', '.'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "X Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHorizontalOThenOWon() {
        char[][] test = {{'X', 'O', 'X'}, {'O', 'O', 'O'}, {'O', 'O', '.'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "O Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenVerticalXThenXWon() {
        char[][] test = {{'.', 'X', '.'}, {'O', 'X', 'X'}, {'O', 'X', '.'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "X Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenVerticalOThenOWon() {
        char[][] test = {{'X', 'O', 'O'}, {'.', 'X', 'O'}, {'.', 'O', 'O'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "O Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDiagonalXThenNothing() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'X', '.'}, {'O', 'O', 'X'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDiagonalOThenNothing() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'O', '.'}, {'.', 'X', 'O'}};
        init(test);
        sidesChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }
}