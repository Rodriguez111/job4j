package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PaintTest {
    private PrintStream original;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

   @Before
   public void loadOut() {
       System.out.println("execute before method");
        original =  System.out;
    }

    @After
    public void backOutput() {
        System.out.println("execute after method");
        System.setOut(this.original);
    }

    @Test
    public void whenDrawSquare() {
        Paint paint = new Paint();
        paint.setShape(new Square());
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        paint.executeShape();
        String actual = baos.toString();
        String lineSeparator = System.lineSeparator();
        String expected = new StringBuilder()
                .append("oooooooo")
                .append(lineSeparator)
                .append("0      0")
                .append(lineSeparator)
                .append("0      0")
                .append(lineSeparator)
                .append("oooooooo")
                .append(lineSeparator).toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDrawTriangle() {
        Paint paint = new Paint();
        paint.setShape(new Triangle());
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        paint.executeShape();
        String actual = baos.toString();
        String luneSeparator = System.lineSeparator();
        String expected = new StringBuilder()
                .append("     o     ")
                .append(luneSeparator)
                .append("    o o    ")
                .append(luneSeparator)
                .append("   o   o   ")
                .append(luneSeparator)
                .append("  o     o  ")
                .append(luneSeparator)
                .append(" o       o ")
                .append(luneSeparator)
                .append("ooooooooooo")
                .append(luneSeparator).toString();
        assertThat(actual, is(expected));
    }

}
