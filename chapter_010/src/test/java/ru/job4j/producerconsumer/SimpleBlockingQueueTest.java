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
                synchronized (simpleBlockingQueue) {
                    if (!simpleBlockingQueue.isFull()) {
                        simpleBlockingQueue.offer(i);
                        simpleBlockingQueue.notifyAll();
                    } else {
                        while (simpleBlockingQueue.isFull()) {
                            try {
                                simpleBlockingQueue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        i--;
                    }
                }
            }
        }
    }

    private class Consumer extends Thread {
        private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
        private final List<Integer> result = new ArrayList<>();
        private boolean isStopped = false;

        public Consumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
            this.simpleBlockingQueue = simpleBlockingQueue;
        }

        @Override
        public void run() {
            while (!isStopped) {
                consume();
            }
        }

        private void consume() {
            synchronized (simpleBlockingQueue) {
                if (!simpleBlockingQueue.isEmpty()) {
                    this.result.add(simpleBlockingQueue.poll());
                    simpleBlockingQueue.notifyAll();
                } else {
                    while (simpleBlockingQueue.isEmpty() && !isStopped) {
                        try {
                            simpleBlockingQueue.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public void setStopped(boolean stopped) {
            isStopped = stopped;
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
        Thread.sleep(500);
        consumer.setStopped(true);
        consumer.join();

        Assert.assertThat(consumer.getResultSize(), is(1000000));
    }
}