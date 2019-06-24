package ru.job4j.nonblockingcash;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class NonBlockingCashTest {
    private class TestClass extends Thread {
        private final NonBlockingCash nonBlockingCash;
        private AtomicReference<Exception> exception = new AtomicReference<>();

        public TestClass(NonBlockingCash nonBlockingCash) {
            this.nonBlockingCash = nonBlockingCash;
        }

        void updating() {
            Base base = new Base(1);
            try {
                nonBlockingCash.update(base);
            } catch (OptimisticException e) {
                exception.set(e);
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                updating();
            }
        }

        public AtomicReference<Exception> getException() {
            return exception;
        }
    }

//    @Test
//    public void when() throws InterruptedException {
//        Base base = new Base(1);
//        NonBlockingCash nonBlockingCash = new NonBlockingCash();
//        nonBlockingCash.add(base);
//        TestClass thread1 = new TestClass(nonBlockingCash);
//        thread1.start();
//        TestClass thread2 = new TestClass(nonBlockingCash);
//        thread2.start();
//        TestClass thread3 = new TestClass(nonBlockingCash);
//        thread3.start();
//        TestClass thread4 = new TestClass(nonBlockingCash);
//        thread4.start();
//        TestClass thread5 = new TestClass(nonBlockingCash);
//        thread5.start();
//
//        for (int i = 0; i < 10_000; i++) {
//            Base newBase = new Base(1);
//            try {
//                nonBlockingCash.update(newBase);
//            } catch (OptimisticException e) {
//                /*NOP*/
//            }
//        }
//        thread1.join();
//        thread2.join();
//        thread3.join();
//        thread4.join();
//        thread5.join();
//        Assert.assertThat(thread1.getException().get().getMessage(), is("Version does not match"));
//    }

}