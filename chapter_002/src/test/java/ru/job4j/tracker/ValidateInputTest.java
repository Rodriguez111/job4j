package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ValidateInputTest {
    PrintStream original;
    PrintStream newStream;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();


    @Before
    public void saveStream() {
        original = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @After
    public void restoreStream() {
        System.setOut(this.original);
    }

    @Test
    public void whenIsNotNumber() {
       ValidateInput validateInput = new ValidateInput(new StubInput(new String[] {"invalid", "2"}));
       validateInput.ask("Enter",  2);
       String expected = "Entered value is not number" + System.lineSeparator();
       String actual = baos.toString();
       assertThat(actual, is(expected));
    }

    @Test
    public void whenOutOfMenuRange() {
        ValidateInput validateInput = new ValidateInput(new StubInput(new String[] {"10", "2"}));
        validateInput.ask("Enter",  2);
        String expected = "You must select key from menu range" + System.lineSeparator();
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }



}
