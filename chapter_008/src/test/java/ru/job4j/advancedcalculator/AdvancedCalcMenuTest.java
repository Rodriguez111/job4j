package ru.job4j.advancedcalculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.calculator.CalcMenu;
import ru.job4j.calculator.FakeInput;
import ru.job4j.calculator.Input;
import ru.job4j.calculator.InteractCalc;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AdvancedCalcMenuTest {
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
                .append("^ - Exponentiation")
                .append(ls)
                .append("r - Root of the power")
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
    public void when2Exp5Then32() {
        List<String> commands = new ArrayList<>();
        commands.add("^");
        commands.add("2");
        commands.add("5");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new AdvancedCalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "2.0 ^ 5.0 = 32.0" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenExtractRoot4From81Then3() {
        List<String> commands = new ArrayList<>();
        commands.add("r");
        commands.add("81");
        commands.add("4");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new AdvancedCalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "81.0 r 4.0 = 3.0000000000000004" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenExtractRoot2From81Exp2Then81() {
        List<String> commands = new ArrayList<>();
        commands.add("r");
        commands.add("81");
        commands.add("2");
        commands.add("^");
        commands.add("");
        commands.add("2");
        commands.add("exit");
        Input input = new FakeInput(commands);
        InteractCalc calc = new InteractCalc(input, new AdvancedCalcMenu());
        calc.startCalc();

        String actual = baos.toString();
        String expected = printMenu() + "81.0 r 2.0 = 9.000000000000002" + ls + printSeparator() + printMenu()
                + "9.000000000000002 ^ 2.0 = 81.00000000000003" + ls + printSeparator() + printMenu();
        assertThat(actual, is(expected));
    }

}