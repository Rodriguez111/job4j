package ru.job4j.generator;

import ru.job4j.generator.exceptions.NotEnoughKeysForProcessingException;
import ru.job4j.generator.exceptions.RedundantKeyInMapException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements GenerateInterface {
    private final  Pattern pattern;

    public SimpleGenerator(String patternString) {
        this.pattern = Pattern.compile(patternString);
    }

    /**
     * Counts unique amount of patterns for replacement in the original string.
     * @param string - original string.
     * @return - unique amount of patterns.
     */
    private int occurrenceCount(String string) {
        Set<String> occurrences = new HashSet<>();
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            occurrences.add(matcher.group());
        }
        return occurrences.size();
    }

    /**
     * Replacing words in pattern, described in patternString field;
     * @param string - source line in which to replace patterns.
     * @param pairs - map with keys and values for replacement.
     * @return - result line with replaced patterns.
     */
    @Override
    public String generate(String string, Map<String, String> pairs) {
        int occurrences = occurrenceCount(string);
        keyMappingValidation(occurrences, pairs.size());

        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String extractWord = matcher.group().substring(2, matcher.group().length() - 1);
            string = string.replace(matcher.group(), pairs.get(extractWord));

        }
        return string;
    }

    /**
     * Generates exceptions if pairs amount do not match amount of occurrences.
     * @param occurrenceCount - amount of unique patterns in the original string.
     * @param pairsPresent - amount of pairs in the map.
     */
    private void keyMappingValidation(int occurrenceCount, int pairsPresent) {
        if (occurrenceCount > pairsPresent) {
            throw new NotEnoughKeysForProcessingException("Not enough data for replacement");
        }
        if (occurrenceCount < pairsPresent) {
            throw new RedundantKeyInMapException("There are to much keys in the map");
        }
    }
}
