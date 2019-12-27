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

public class DiagonalsCheckTest {
    private DiagonalsChecker diagonalsChecker;
    private String ls = System.lineSeparator();

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
        this.diagonalsChecker = new DiagonalsCheck(testField.getField());
    }

    @Test
    public void whenMainDiagonalIsXThenTrue() {
        char[][] test = {{'X', 'O', '.'}, {'X', 'X', '.'}, {'.', 'O', 'X'}};
        init(test);
        boolean actual = diagonalsChecker.checkDiagonals('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondaryDiagonalIsXThenTrue() {
        char[][] test = {{'.', 'O', 'X'}, {'X', 'X', '.'}, {'X', 'O', 'X'}};
        init(test);
        boolean actual = diagonalsChecker.checkDiagonals('X');
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMainDiagonalHavePointThenFalse() {
        char[][] test = {{'X', 'O', '.'}, {'X', '.', '.'}, {'.', 'O', 'X'}};
        init(test);
        boolean actual = diagonalsChecker.checkDiagonals('X');
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSecondaryDiagonalHavePointThenFalse() {
        char[][] test = {{'.', 'O', 'X'}, {'X', 'X', '.'}, {'.', 'O', 'X'}};
        init(test);
        boolean actual = diagonalsChecker.checkDiagonals('X');
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDiagonalXThenXWon() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'X', '.'}, {'O', 'O', 'X'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "X Won!" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDiagonalOThenOWon() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'O', '.'}, {'.', 'X', 'O'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "O Won!" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHorizontalXThenNothing() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'X', 'X'}, {'O', 'O', '.'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenHorizontalOThenNothing() {
        char[][] test = {{'X', 'O', 'X'}, {'O', 'O', 'O'}, {'O', '.', '.'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenVerticalXThenNothing() {
        char[][] test = {{'X', 'O', 'X'}, {'X', '.', 'X'}, {'X', 'O', '.'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenVerticalOThenNothing() {
        char[][] test = {{'X', 'O', 'X'}, {'.', 'O', 'O'}, {'O', 'O', '.'}};
        init(test);
        diagonalsChecker.isWin('X', 'O');
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

}