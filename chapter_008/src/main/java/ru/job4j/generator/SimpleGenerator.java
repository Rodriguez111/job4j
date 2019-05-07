package ru.job4j.generator;

import javafx.util.Pair;

import java.util.regex.Pattern;

public class SimpleGenerator implements GenerateInterface {
    private final Pattern keys;

    public SimpleGenerator(Pattern keys) {
        this.keys = keys;
    }

    @Override
    public String generate(String string, Pair[] pairs) {
        return null;
    }
}
