package ru.job4j.ticktacktoe.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.ticktacktoe.gamefield.Field;
import ru.job4j.ticktacktoe.gamefield.GameField;
import ru.job4j.ticktacktoe.gamefield.TestField;
import ru.job4j.ticktacktoe.input.ConsoleInput;
import ru.job4j.ticktacktoe.output.ConsoleOutput;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserComputerBasicLogicTest {
    private UserComputerBasicLogic userComputerLogic;
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

    public void init(char[][] test, WinChecker winChecker) {
        Field testField = new TestField(test);
        ConsoleInput input = new ConsoleInput();
        this.userComputerLogic = new UserComputerBasicLogic(input, testField, winChecker, false);
    }

    @Test
    public void whenNoEmptyFieldThenFalse() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'X', 'O'}, {'O', 'O', 'X'}};
        SidesCheck sidesCheck = new SidesCheck(test);
        init(test, sidesCheck);
        boolean actual = userComputerLogic.canMove();
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenEmptyFieldThenTrue() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'X', '.'}, {'O', 'O', 'X'}};
        SidesCheck sidesCheck = new SidesCheck(test);
        init(test, sidesCheck);
        boolean actual = userComputerLogic.canMove();
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveToTheEmptyFieldReturnsTrue() {
        ConsoleInput input = new ConsoleInput();
        GameField field = new GameField(3, new ConsoleOutput());
        SidesCheck sidesCheck = new SidesCheck(field.getField());
        UserComputerBasicLogic userComputerLogic = new UserComputerBasicLogic(input, field, sidesCheck, false);
        boolean actual = userComputerLogic.move('X', 0, 0);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenMoveToTheEngagedFieldReturnsFalse() {
        ConsoleInput input = new ConsoleInput();
        GameField field = new GameField(3, new ConsoleOutput());
        SidesCheck sidesCheck = new SidesCheck(field.getField());
        UserComputerBasicLogic userComputerLogic = new UserComputerBasicLogic(input, field, sidesCheck, false);
        userComputerLogic.move('X', 0, 0);
        boolean actual = userComputerLogic.move('X', 0, 0);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsSidesCheckAndAllHorizontalIsXThenXWin() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'X', 'X'}, {'O', 'O', '.'}};
        SidesCheck sidesCheck = new SidesCheck(test);
        init(test, sidesCheck);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "X Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsSidesCheckAndAllVerticalIsOThenOWin() {
        char[][] test = {{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'O', 'O', '.'}};
        SidesCheck sidesCheck = new SidesCheck(test);
        init(test, sidesCheck);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "O Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsSidesCheckAndDiagonalIsOThenNothing() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'O', 'X'}, {'O', '.', 'O'}};
        WinChecker winChecker = new SidesCheck(test);
        init(test, winChecker);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsDiagonalsCheckAndDiagonalIsOThenOWin() {
        char[][] test = {{'O', 'O', 'X'}, {'X', 'O', 'X'}, {'O', '.', 'O'}};
        WinChecker winChecker = new DiagonalsCheck(test);
        init(test, winChecker);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "O Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsDiagonalsCheckAndSideIsOThenNothing() {
        char[][] test = {{'O', 'O', 'O'}, {'X', 'O', 'X'}, {'X', '.', '.'}};
        WinChecker winChecker = new DiagonalsCheck(test);
        init(test, winChecker);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsSidesAndDiagonalsCheckAndSideIsOThenOWin() {
        char[][] test = {{'O', 'O', 'O'}, {'X', 'O', 'X'}, {'X', '.', '.'}};
        WinChecker winChecker = new SidesAndDiagonalsCheck(new SidesCheck(test), new DiagonalsCheck(test));
        init(test, winChecker);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "O Won!\r\n";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenCheckerIsSidesAndDiagonalsCheckAndSideIsXThenXWin() {
        char[][] test = {{'O', 'O', 'X'}, {'O', 'X', 'X'}, {'X', '.', '.'}};
        WinChecker winChecker = new SidesAndDiagonalsCheck(new SidesCheck(test), new DiagonalsCheck(test));
        init(test, winChecker);
        userComputerLogic.isWin();
        String actual = baos.toString();
        String expected = "X Won!\r\n";
        assertThat(actual, is(expected));
    }

}