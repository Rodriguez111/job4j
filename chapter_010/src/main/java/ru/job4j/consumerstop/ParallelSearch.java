package ru.job4j.consumerstop;
import ru.job4j.producerconsumer.SimpleBlockingQueue;
public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);

        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        System.out.println(queue.poll());
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 10; index++) {
                        queue.offer(index);
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}