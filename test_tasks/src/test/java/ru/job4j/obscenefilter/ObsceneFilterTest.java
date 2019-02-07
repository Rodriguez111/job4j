package ru.job4j.obscenefilter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ObsceneFilterTest {

    @Test
    public void shouldReturnListOfStringsWithoutObsceneWords() {
        ObsceneFilter obsceneFilter = new ObsceneFilter();
        List<String> stringsToCensor = new ArrayList<>();
        stringsToCensor.add("Здесь слово1 есть");
        stringsToCensor.add("Здесь есть слово1 а так же и слово2 тоже есть");
        stringsToCensor.add("А здесь содержится слово3 и еще слово1 и все");

        List<String> obsceneWords  = new ArrayList<>();
        obsceneWords.add("слово1");
        obsceneWords.add("слово2");
        obsceneWords.add("слово3");

        List<String> actual = obsceneFilter.process(stringsToCensor, obsceneWords);
        List<String> expected = List.of("Здесь есть", "Здесь есть а так же и тоже есть", "А здесь содержится и еще и все");

        assertThat(actual, is(expected));

    }


}