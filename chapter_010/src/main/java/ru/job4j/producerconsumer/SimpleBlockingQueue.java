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

    private boolean isFull = false;

    public SimpleBlockingQueue(final int bound) {
        this.bound = bound;
    }

    public synchronized void offer(final T value) {
        if (this.queue.size() < this.bound) {
            this.queue.offer(value);
            if (this.queue.size() == bound) {
                this.isFull = true;
            }
        }
    }

    public synchronized T poll() {
        this.isFull = false;
        return this.queue.poll();
    }

    public boolean isFull() {
        return isFull;
    }

    public synchronized boolean isEmpty() {
        return this.queue.size() == 0;

    }
}
