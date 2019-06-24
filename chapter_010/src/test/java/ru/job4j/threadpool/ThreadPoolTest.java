package ru.job4j.threadpool;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {
    private static volatile int count = 0;

    public static Runnable job(int limit, boolean toIncrement) {
        return () -> {
            for (int i = 0; i < limit; i++) {
                synchronized (ThreadPool.class) {
                    if (toIncrement) {
                        count++;
                    } else {
                        count--;
                    }
                }
            }
        };
    }

    @Test
    public void whenIncrementIn2TasksAndDecrementIn2AndShutdownThenCountIs0() {
        int limit = 1000000;
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(job(limit, true));
        threadPool.work(job(limit, false));
        threadPool.work(job(limit, true));
        threadPool.work(job(limit, false));
        threadPool.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat(count, is(0));
    }
}