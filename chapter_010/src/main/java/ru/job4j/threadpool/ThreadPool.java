package ru.job4j.threadpool;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import javax.crypto.interfaces.PBEKey;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private boolean isRunning = true;

    public ThreadPool() {
        initPool();
    }

    private void initPool() {
        int processorsAmount = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < processorsAmount; i++) {
            Thread thread = new TaskProcessor();
            thread.start();
            threads.add(thread);
        }
    }

    private class TaskProcessor extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                while (!tasks.isEmpty()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        interrupt();
                        break;
                    }
                }
            }
        }
    }

    public void work(Runnable job) {
        this.tasks.offer(job);
    }

    public void shutdown() {
        isRunning = false;
    }
}
