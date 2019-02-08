package ru.job4j.wordscompare;

import java.util.HashSet;
import java.util.Set;

public class WordEquality {

    public boolean wordsAreEqual(String word1, String word2) {
        Set<String> set = new HashSet<>();
        set.add(word1);
        set.add(word2);
        return set.size() == 1;
    }

}
