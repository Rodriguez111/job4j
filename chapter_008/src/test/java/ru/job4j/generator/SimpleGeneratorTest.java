package ru.job4j.generator;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGeneratorTest {


    @Test
    public void whenSend() {
        Pattern pattern = Pattern.compile(".*\\$\\{.+\\}.*");
        GenerateInterface generator = new SimpleGenerator(pattern);


    }

}