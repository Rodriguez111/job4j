package ru.job4j.filesearch;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.filesearch.argparser.exceptions.ArgumentException;
import ru.job4j.filesearch.argparser.exceptions.CommandIsNotProvidedException;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgsTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private final String source = path + "/00Test";
    private final String outputDir = path + "/00Output";
    private final String outputFile = outputDir + "/log.txt";
    private final String ls = System.lineSeparator();


    @Before
    public void init() throws IOException {
        createTestFilesStructure();
    }

    public String[] generateArgsByMask() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--" + source;
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "--m";
        args[5] = "-o";
        args[6] = "--" + outputFile;
        return args;
    }


    public void createTestFilesStructure() throws IOException {
        new File(source).mkdirs();
        new File(outputDir).mkdirs();
        new File(source + "/text.pdf").createNewFile();
        new File(source + "/text.txt").createNewFile();
        new File(source + "/file.java").createNewFile();
        new File(source + "/file.xml").createNewFile();
        new File(source + "/newfolder").mkdirs();
        new File(source + "/newfolder/text.pdf").createNewFile();
        new File(source + "/newfolder/text.txt").createNewFile();
        new File(source + "/newfolder/file.xml").createNewFile();
        new File(source + "/newfolder/file.java").createNewFile();
        new File(source + "/newfolder/newfolderztext2").mkdirs();
        new File(source + "/newfolder/newfolderztext2/text.pdf").createNewFile();
        new File(source + "/newfolder/newfolderztext2/text.txt").createNewFile();
        new File(source + "/newfolder/newfolderztext2/file.xml").createNewFile();
        new File(source + "/newfolder/newfolderztext2/file.java").createNewFile();
    }

    @Test
    public void shouldThrowCommandIsNotProvidedExceptionWhenUnknownFirstParameter() {
        String[] args = new String[7];
        args[0] = "-unknown";
        args[1] = "--fakePath";
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "--m";
        args[5] = "-o";
        args[6] = "--fakeOutputFile";

        Throwable exception = assertThrows(CommandIsNotProvidedException.class, () -> {
            Args.main(args);
        });
        assertEquals("No such command was provided: -unknown", exception.getMessage());

    }

    @Test
    public void shouldThrowCommandIsNotProvidedExceptionWhenUnknownSecondParameter() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--fakePath";
        args[2] = "-unknown";
        args[3] = "--zte";
        args[4] = "--f";
        args[5] = "-o";
        args[6] = "--fakeOutputFile";

        Throwable exception = assertThrows(CommandIsNotProvidedException.class, () -> {
            Args.main(args);
        });
        assertEquals("No such command was provided: -unknown", exception.getMessage());
    }

    @Test
    public void shouldThrowCommandIsNotProvidedExceptionWhenNoArgumentForSecondParameter() {
        String[] args = new String[6];
        args[0] = "-d";
        args[1] = "--fakePath";
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "-o";
        args[5] = "--fakeOutputFile";

        Throwable exception = assertThrows(ArgumentException.class, () -> {
            Args.main(args);
        });
        assertEquals("Command -n must have 2 argument(s)", exception.getMessage());
    }


    @Test
    public void shouldThrowCommandIsNotProvidedExceptionWhenUnknownThirdParameter() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--fakePath";
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "--f";
        args[5] = "-unknown";
        args[6] = "--fakeOutputFile";

        Throwable exception = assertThrows(CommandIsNotProvidedException.class, () -> {
            Args.main(args);
        });
        assertEquals("No such command was provided: -unknown", exception.getMessage());
    }

    @Test
    public void shouldThrowCommandIsNotProvidedExceptionWhenUnknownArgumentForSecondParameter() {
        String[] args = new String[7];
        args[0] = "-d";
        args[1] = "--fakePath";
        args[2] = "-n";
        args[3] = "--zte";
        args[4] = "--unknown";
        args[5] = "-unknown";
        args[6] = "--fakeOutputFile";

        Throwable exception = assertThrows(ArgumentException.class, () -> {
            Args.main(args);
        });
        assertEquals("Unknown argument:     --unknown: null", exception.getMessage());
    }

    @Test
    public void shouldFillFieldsWithParametersWhenArgumentsOk() {
        Args.main(generateArgsByMask());
        Args args1 = new Args();
        StringBuilder sb = new StringBuilder();
        sb.append(args1.getRoodDir())
                .append(args1.getSearchPattern())
                .append(args1.getSearchPatternType())
                .append(args1.getOutputFilePath());

        String actual = sb.toString();

        sb.setLength(0);
        sb.append(source)
                .append("zte")
                .append("m")
                .append(outputFile);

        String expected = sb.toString();

        assertEquals(actual, expected);
    }

}