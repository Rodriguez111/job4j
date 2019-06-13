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
        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.queue.offer(value);
        this.notifyAll();
    }

    public synchronized T poll() {
        while (isEmpty() && !Thread.currentThread().isInterrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
