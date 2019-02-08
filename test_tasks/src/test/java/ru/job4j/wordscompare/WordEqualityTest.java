package ru.job4j.wordscompare;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class WordEqualityTest {

    WordEquality wordsCompare = new WordEquality();

    @Test
    public void shouldReturnTrueWhenWordsAreEquals() {
        String word1 = "GoodDay";
        String word2 = "GoodDay";
      boolean actual = wordsCompare.wordsAreEqual(word1, word2);
      boolean expected = true;
      assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnFalseWhenWordsAreEquals() {
        String word1 = "GoodDay";
        String word2 = "Gooday";
        boolean actual = wordsCompare.wordsAreEqual(word1, word2);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}