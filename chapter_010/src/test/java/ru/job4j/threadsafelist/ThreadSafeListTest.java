package ru.job4j.threadsafelist;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.list.SimpleArrayList;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class ThreadSafeListTest {
    private class TestClass extends Thread {
        private final ThreadSafeList<Integer> threadSafeList;
        private final List<Integer> resultList = new ArrayList<>();

        public TestClass(ThreadSafeList<Integer> threadSafeList) {
            this.threadSafeList = threadSafeList;
        }

        @Override
        public void run() {
            for (Integer eachElement : this.threadSafeList) {
                resultList.add(eachElement);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public int getResultSize() {
            return resultList.size();
        }

        public int getOriginalSize() {
            return threadSafeList.size();
        }

        public int getThreadSafeSize() {
            return resultList.size();
        }

    }

    @Test
    public void whenStartInThreadListOf10ElementsAndSimultaneouslyAddingAdding10MoreThenSafeSize10AndOriginalSize20() throws InterruptedException {
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();
        ThreadSafeList<Integer> threadSafeList = new ThreadSafeList<>(simpleArrayList);

        for (int i = 0; i < 10; i++) {
            threadSafeList.add(i);
        }

        TestClass thread1 = new TestClass(threadSafeList);
        thread1.start();

        Thread.sleep(20);

        for (int i = 0; i < 10; i++) {
            threadSafeList.add(i);
        }
        thread1.join();

        int actualSafeSize = thread1.getThreadSafeSize();
        int actualOriginalSize = thread1.getOriginalSize();

        Assert.assertThat(actualSafeSize, is(10));
        Assert.assertThat(actualOriginalSize, is(20));
    }
}






