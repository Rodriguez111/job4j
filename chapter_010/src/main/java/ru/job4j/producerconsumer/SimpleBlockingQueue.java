package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int bound;

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    public synchronized void offer(T value) {

        try {
            while (isFull()) {
                this.wait();
            }
            this.queue.offer(value);
            this.notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public synchronized T poll() throws InterruptedException {
        while (isEmpty() && !Thread.currentThread().isInterrupted()) {
            this.wait();
        }
        T result = this.queue.poll();
        this.notifyAll();
        return result;
    }

    private boolean isFull() {
        return this.queue.size() == bound;
    }

    public synchronized boolean isEmpty() {
        return this.queue.size() == 0;
    }
}
