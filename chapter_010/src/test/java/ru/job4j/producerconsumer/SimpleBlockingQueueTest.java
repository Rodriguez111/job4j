package ru.job4j.producerconsumer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    private class Producer extends Thread {
        private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
        private final int amount;


        public Producer(SimpleBlockingQueue<Integer> simpleBlockingQueue, int amount) {
            this.simpleBlockingQueue = simpleBlockingQueue;
            this.amount = amount;
        }

        @Override
        public void run() {
            produce(this.amount);
        }

        private void produce(int amount) {
            for (int i = 0; i < amount; i++) {
                simpleBlockingQueue.offer(i);
            }
        }
    }

    private class Consumer extends Thread {
        private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
        private final List<Integer> result = new ArrayList<>();

        public Consumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
            this.simpleBlockingQueue = simpleBlockingQueue;
        }

        @Override
        public void run() {
            while (!isInterrupted() || !simpleBlockingQueue.isEmpty()) {
                consume();
            }

        }

        private void consume() {
            try {
                result.add(simpleBlockingQueue.poll());
            } catch (InterruptedException e) {
                interrupt();
            }
        }

        public int getResultSize() {
            return result.size();
        }
    }

    @Test
    public void whenProduce1mlnThenConsume1mln() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(100);

        Consumer consumer = new Consumer(simpleBlockingQueue);
        Producer producer = new Producer(simpleBlockingQueue, 1000000);

        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(consumer.getResultSize(), is(1000000));
    }
}