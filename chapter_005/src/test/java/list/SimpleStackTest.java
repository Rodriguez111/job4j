package list;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void init() {
        stack = new SimpleStack<>();
    }

    @Test
    public void shouldPollThreeTwoOneNullWhenPushThreeElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.poll(), is(IsNull.nullValue()));
    }

    @Test
    public void shouldPeekThreeThreeThreeWhenPeekThreeTimes() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.peek(), is(3));
        assertThat(stack.peek(), is(3));
        assertThat(stack.peek(), is(3));
    }



}