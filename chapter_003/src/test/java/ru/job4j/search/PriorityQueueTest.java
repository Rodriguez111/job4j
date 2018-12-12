package ru.job4j.search;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDescription(), is("urgent"));
    }

    @Test
    public void checkHoleQueue() {
        Task task5 = new Task("5", 5);
        Task task1 = new Task("1", 1);
        Task task3 = new Task("3", 3);
        Task task8 = new Task("8", 8);
        Task task0 = new Task("0", 0);
        Task task4 = new Task("4", 4);



        PriorityQueue queue = new PriorityQueue();
        queue.put(task5);
        queue.put(task1);
        queue.put(task3);
        queue.put(task8);
        queue.put(task0);
        queue.put(task4);



        List<Task> actual = new ArrayList<>();
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());
        actual.add(queue.take());

        List<Task> expected = new ArrayList<>();
        expected.add(task0);
        expected.add(task1);
        expected.add(task3);
        expected.add(task4);
        expected.add(task5);
        expected.add(task8);

        assertThat(actual, is(expected));
    }



}
