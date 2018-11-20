package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void eightEntriesRemainsThree() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] string = new String[] {"Привет", "Мир", "Мир", "Привет", "Супер", "Привет", "Мир", "Привет"};
        String[] actual = arrayDuplicate.remove(string);
        String[] expected = new String[] {"Привет", "Мир", "Супер"};
        assertThat(actual, is(expected));
    }

    @Test
    public void fiveEntriesRemainsThree() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] string = new String[] {"Привет", "Мир", "Мир", "Привет", "Супер"};
        //String[] string = new String[] {"Супер", "Супер", "Привет", "Супер"};
        String[] actual = arrayDuplicate.remove(string);
        String[] expected = new String[] {"Привет", "Мир", "Супер"};
        assertThat(actual, is(expected));
    }

    @Test
    public void fourEntriesRemainsTwo() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] string = new String[] {"Супер", "Супер", "Привет", "Супер"};
        String[] actual = arrayDuplicate.remove(string);
        String[] expected = new String[] {"Супер", "Привет"};
        assertThat(actual, is(expected));
    }

    @Test
    public void twoEntriesRemainsOne() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] string = new String[] {"Супер", "Супер"};
        String[] actual = arrayDuplicate.remove(string);
        String[] expected = new String[] {"Супер"};
        assertThat(actual, is(expected));
    }

    @Test
    public void oneEntryRemainsOne() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] string = new String[] {"Супер"};
        String[] actual = arrayDuplicate.remove(string);
        String[] expected = new String[] {"Супер"};
        assertThat(actual, is(expected));
    }


}
