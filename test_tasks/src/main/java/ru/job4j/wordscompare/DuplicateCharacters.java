package ru.job4j.wordscompare;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateCharacters {
    public List<Character> findDuplicateCharacters(String word) {
        Map<Character, Integer> map = new HashMap<>();
        word.chars().forEach(each -> {
            if (map.containsKey((char) each)) {
               map.put((char) each, map.get((char) each) + 1);
            } else {
                map.put((char) each, 1);
            }
        });
return map.entrySet().stream()
        .filter(each -> each.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    }

}
