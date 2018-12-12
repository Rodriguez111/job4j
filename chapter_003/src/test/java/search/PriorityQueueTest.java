package search;
import org.junit.Test;
import ru.job4j.search.Person;
import ru.job4j.search.PhoneDictionary;
import ru.job4j.search.PriorityQueue;
import ru.job4j.search.Task;

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
    public void whenHigherPriority2() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("very urgent", 0));
        Task result = queue.take();
        assertThat(result.getDescription(), is("very urgent"));
    }

}
