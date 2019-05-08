package ru.job4j.generator;

import org.junit.Test;
import ru.job4j.generator.exceptions.NotEnoughKeysForProcessingException;
import ru.job4j.generator.exceptions.RedundantKeyInMapException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SimpleGeneratorTest {
    private final String patternString = "\\$\\{%s\\}";
    private final GenerateInterface generator = new SimpleGenerator(patternString);

    @Test
    public void whenSendTwoKeysAntTwoWordsThenReplaceTwoWords() {
        String phrase = "I am ${name}, Who are ${subject}?";
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Petr");
        pairs.put("subject", "you");

        String actual = generator.generate(phrase, pairs);
        String expected = "I am Petr, Who are you?";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSendOneKeyAndOneWordThenReplaceOneWord() {
        String phrase = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> pairs = new HashMap<>();
        pairs.put("sos", "Aaa");

        String actual = generator.generate(phrase, pairs);
        String expected = "Help, Aaa, Aaa, Aaa";

        assertThat(actual, is(expected));
    }

    @Test
    public void whenSendOneKeyAndTwoWordThenNotEnoughKeysForProcessingException() {
        String phrase = "I am ${name}, Who are ${subject}?";
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Petr");

        Throwable exception = assertThrows(NotEnoughKeysForProcessingException.class, () -> {
            generator.generate(phrase, pairs);
        });
        assertEquals("Not enough data for replacement", exception.getMessage());
    }

    @Test
    public void whenSendTwoPairsAndOneWordThenRedundantKeyInMapException() {
        String phrase = "I am ${name}";
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Petr");
        pairs.put("subject", "you");

        Throwable exception = assertThrows(RedundantKeyInMapException.class, () -> {
            generator.generate(phrase, pairs);
        });
        assertEquals("There are to much keys in the map", exception.getMessage());
    }
}