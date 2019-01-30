package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleUnidirectionalListTest {

    private SimpleUnidirectionalList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleUnidirectionalList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteFirstElementThenFirstElementBecomeTwoAndReturnThree() {
      int actual = list.delete();

        assertThat(actual, is(3));
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenDeleteFromTheEmptyListThenNoSuchElementException() {
        list = new SimpleUnidirectionalList<>();
        Throwable exception = assertThrows(NoSuchElementException.class, ()-> {
            list.delete();

        });
    }

}