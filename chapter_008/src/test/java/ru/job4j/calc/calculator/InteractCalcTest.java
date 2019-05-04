package ru.job4j.calc.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class InteractCalcTest {
    private final String ls = System.lineSeparator();
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

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Choose operation:")
                .append(ls)
                .append("+ - Add")
                .append(ls)
                .append("- - Subtract")
                .append(ls)
                .append("* - Multiple")
                .append(ls)
                .append("/ - Divide")
                .append(ls)
                .append("exit - Exit")
                .append(ls);
        return sb.toString();
    }

    public String printSeparator() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================")
                .append(ls);
        return sb.toString();
    }

    @Test
    public void when2Plus5Then7() {
        List<String> commands = new ArrayList<>();
        commands.add("+");
        commands.add("2");
        commands.add("5");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "2.0 + 5.0 = 7.0" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void when10Minus20ThenMinus10() {
        List<String> commands = new ArrayList<>();
        commands.add("-");
        commands.add("10");
        commands.add("20");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "10.0 - 20.0 = -10.0" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void when4MultiplyMinus5ThenMinus20() {
        List<String> commands = new ArrayList<>();
        commands.add("*");
        commands.add("4");
        commands.add("-5");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "4.0 * -5.0 = -20.0" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void when4Divide5Then08() {
        List<String> commands = new ArrayList<>();
        commands.add("/");
        commands.add("4");
        commands.add("5");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "4.0 / 5.0 = 0.8" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void when4Divide5Plus4Then4Point8() {
        List<String> commands = new ArrayList<>();
        commands.add("/");
        commands.add("4");
        commands.add("5");
        commands.add("+");
        commands.add("");
        commands.add("4");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "4.0 / 5.0 = 0.8" + ls + printSeparator() + printMenu()
                + "0.8 + 4.0 = 4.8" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void when4MultiplyMinus5Divide2ThenMinus10() {
        List<String> commands = new ArrayList<>();
        commands.add("*");
        commands.add("4");
        commands.add("-5");
        commands.add("/");
        commands.add("");
        commands.add("2");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new CalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "4.0 * -5.0 = -20.0" + ls + printSeparator() + printMenu()
                + "-20.0 / 2.0 = -10.0" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }


}