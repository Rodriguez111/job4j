package ru.job4j.list;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> listOfIntegers;
    @Before
    public void init() {
        listOfIntegers = new SimpleLinkedList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);
    }

    @Test
    public void shouldReturnThreeElements123WhenAddThirdElement3() {
        listOfIntegers.add(3);
        assertThat(listOfIntegers.get(0), is(1));
        assertThat(listOfIntegers.get(1), is(2));
        assertThat(listOfIntegers.get(2), is(3));
    }

    @Test
    public void shouldReturnOneElement99WhenAddFirstElement99() {
        listOfIntegers = new SimpleLinkedList<>();
        listOfIntegers.add(99);
        assertThat(listOfIntegers.get(0), is(99));

    }





    @Test
    public void shouldThrowConcurrentModificationExceptionWhenGrowArrayDuringIteration() {
        Throwable exception = assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer each : listOfIntegers) {
                if (each == 1) {
                    listOfIntegers.add(10);
                }
            }
        });
    }

}