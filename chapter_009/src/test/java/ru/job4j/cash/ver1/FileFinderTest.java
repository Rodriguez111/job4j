package ru.job4j.cash.ver1;

import ru.job4j.cash.ver1.cashfinders.FileFinder;
import org.junit.Test;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileFinderTest {

    @Test
    public void whenSearchByFileNameAndFilePresentsInCashThenReturnFile() {
        FileFinder fileFinder = new FileFinder();
        Map<String, SoftReference<File>> testMap = new HashMap<>();
        testMap.put("FileName", new SoftReference<>(new File("FileName")));
        Optional<File> result = fileFinder.apply("FileName", testMap);
        File actual = result.get();
        File  expected = new File("FileName");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenSearchByFileNameAndFileNotPresentsInCashThenReturnEmpty() {
        FileFinder fileFinder = new FileFinder();
        Map<String, SoftReference<File>> testMap = new HashMap<>();
        testMap.put("FileName", new SoftReference<>(null));
        Optional<File> actual = fileFinder.apply("FileName", testMap);
        Optional<File>  expected = Optional.empty();
        assertThat(actual, is(expected));
    }
}