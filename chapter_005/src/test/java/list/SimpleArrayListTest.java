package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleArrayListTest {
   private SimpleArrayList<Integer> listOfIntegers;

    @Before
    public void init() {
        listOfIntegers = new SimpleArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
    }
    @Test
    public void shouldReturnThreeElementsWhenAddOneElement() {
        listOfIntegers.add(3);
        assertThat(listOfIntegers.get(0), is(1));
        assertThat(listOfIntegers.get(1), is(2));
        assertThat(listOfIntegers.get(2), is(3));
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionWhenGrowArrayDuringIteration() {
        Throwable exception = assertThrows(ConcurrentModificationException.class, ()-> {
            for (Integer each : listOfIntegers) {
                if (each == 1) {
                    listOfIntegers.add(3);
                }
            }
        });
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenNextInvokedAndNoElementsMore() {
        Iterator iterator = listOfIntegers.iterator();
        Throwable exception = assertThrows(NoSuchElementException.class, ()-> {
            iterator.next();
            iterator.next();
            iterator.next();
        });
    }



}