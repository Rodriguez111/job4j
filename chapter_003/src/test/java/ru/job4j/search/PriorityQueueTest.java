package ru.job4j.search;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDescription(), is("urgent"));
    }

    @Test
    public void checkHoleQueue() {
        var task5 = new Task("5", 5);
        var task1 = new Task("1", 1);
        var task3 = new Task("3", 3);
        var task8 = new Task("8", 8);
        var task0 = new Task("0", 0);
        var task4 = new Task("4", 4);



        var queue = new PriorityQueue();
        queue.put(task5);
        queue.put(task1);
        queue.put(task3);
        queue.put(task8);
        queue.put(task0);
        queue.put(task4);



        var actual = new ArrayList<>();
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());

        var expected = new ArrayList<>();
        expected.add(task0);
        expected.add(task1);
        expected.add(task3);
        expected.add(task4);
        expected.add(task5);
        expected.add(task8);

        assertThat(actual, is(expected));
    }



}
