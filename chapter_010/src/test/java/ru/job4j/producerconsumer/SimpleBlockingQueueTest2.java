package ru.job4j.producerconsumer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest2 {
    final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
    final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

    Thread producer = new Thread(() -> {
        synchronized (queue) {
            for (int i = 0; i < 10; i++) {
                synchronized (queue) {
                    queue.offer(i);
                    queue.notifyAll();
                    while (queue.isFull()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    });

    Thread consumer = new Thread(() -> {

        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    buffer.add(queue.poll());
                    queue.notifyAll();
                }
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
    });

    @Test
    public void whenProduce10ElementsThenConsume10() throws InterruptedException {
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        synchronized (queue) {
            queue.notifyAll();
        }
        consumer.join();
        Assert.assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}