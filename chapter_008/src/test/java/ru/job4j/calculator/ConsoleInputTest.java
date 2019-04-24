package ru.job4j.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleInputTest {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ByteArrayInputStream bais;
    private InputStream originalInput;

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
        System.setIn(originalInput);
    }

    @Test
    public void whenInput1ThenReturn1() {
        bais = new ByteArrayInputStream("1".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        double actual = consoleInput.getFirstValue("Input value").get();
        double expected = 1.0;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInputTenThenAskInputAgainAndThrowExceptionBecauseThereIsNoMoreDataInStream() {
        bais = new ByteArrayInputStream("ten".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            consoleInput.getFirstValue("Input value").get();
        });
        assertEquals("No line found", exception.getMessage());
    }

    @Test
    public void whenInput2ThenReturn2() {
        bais = new ByteArrayInputStream("2".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        double actual = consoleInput.getSecondValue("Input value");
        double expected = 2.0;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInputTwoThenAskInputAgainAndThrowExceptionBecauseThereIsNoMoreDataInStream() {
        bais = new ByteArrayInputStream("two".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            consoleInput.getSecondValue("Input value");
        });
        assertEquals("No line found", exception.getMessage());
    }

    @Test
    public void whenInputPlusThenReturnPlus() {
        bais = new ByteArrayInputStream("+".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        consoleInput.setMenu(new CalcMenu());
        String actual = consoleInput.getOperation("Input value");
        String expected = "+";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInputExitThenReturnExit() {
        bais = new ByteArrayInputStream("exit".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        consoleInput.setMenu(new CalcMenu());
        String actual = consoleInput.getOperation("Input value");
        String expected = "exit";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInputSomeWrongSymbolThenAskInputAgainAndThrowExceptionBecauseThereIsNoMoreDataInStream() {
        bais = new ByteArrayInputStream("!".getBytes());
        ConsoleInput consoleInput = new ConsoleInput(bais);
        consoleInput.setMenu(new CalcMenu());
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            consoleInput.getSecondValue("Input value");
        });
        assertEquals("No line found", exception.getMessage());
    }

}