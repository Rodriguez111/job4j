package list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.core.IsNull;
import org.junit.Test;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Test
    public void shouldReturn12345WhenPush12345() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
    }

    @Test
    public void shouldReturn123Null45WhenPush123PollFourTimesThenPush45PollTwoTimes() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(IsNull.nullValue()));

        queue.push(4);
        queue.push(5);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));

    }



}