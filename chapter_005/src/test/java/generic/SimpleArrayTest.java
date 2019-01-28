package generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
     SimpleArray<Integer> simpleArrayOfIntegers;
    SimpleArray<String> simpleArrayOfStrings;
    @Before
    public void init() {
        simpleArrayOfIntegers = new SimpleArray<>(3);
        simpleArrayOfStrings = new SimpleArray<>(3);
    }


    @Test
    public void shouldReturn10WhenAdd10() {
        simpleArrayOfIntegers.add(10);
        Integer expected = 10;
        Integer actual = simpleArrayOfIntegers.get(0);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturn10WhenSet10() {
        simpleArrayOfIntegers.add(1);
        simpleArrayOfIntegers.add(2);
        simpleArrayOfIntegers.add(3);
        simpleArrayOfIntegers.set(2, 10);
        Integer expected = 10;
        Integer actual = simpleArrayOfIntegers.get(2);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnArrayOf1And3WhenRemoveSecondElement() {
        simpleArrayOfIntegers.add(1);
        simpleArrayOfIntegers.add(2);
        simpleArrayOfIntegers.add(3);

        Integer expected = 3;
        simpleArrayOfIntegers.remove(1);
        Integer actual = simpleArrayOfIntegers.get(1);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnOneWhenGet0Element() {
        simpleArrayOfStrings.add("one");
        String expected = "one";
        String actual = simpleArrayOfStrings.get(0);
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnFalseWhenEmpty() {
        Iterator<Integer>  iterator = simpleArrayOfIntegers.iterator() ;
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void shouldReturnTwoWhenNextInvokedForTheSecondTime() {
        Iterator<String>  iterator = simpleArrayOfStrings.iterator() ;
        simpleArrayOfStrings.add("one");
        simpleArrayOfStrings.add("two");

        String expected = "two";
        iterator.next();
        String actual = iterator.next();
        assertThat(actual, is(expected));

        for(String each : simpleArrayOfStrings) {}
    }
}