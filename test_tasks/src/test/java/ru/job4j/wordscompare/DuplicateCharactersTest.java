package ru.job4j.wordscompare;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DuplicateCharactersTest {

    DuplicateCharacters duplicateCharacters = new DuplicateCharacters();

    @Test
    public void shouldReturnListOfLAndO() {
        String word = "Helloo";
        List<Character> actual = duplicateCharacters.findDuplicateCharacters(word);
        List<Character> expected = List.of('l', 'o');
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnEmptyList() {
        String word = "Helo";
        List<Character> actual = duplicateCharacters.findDuplicateCharacters(word);
        List<Character> expected = List.of();
        assertThat(actual, is(expected));
    }

}