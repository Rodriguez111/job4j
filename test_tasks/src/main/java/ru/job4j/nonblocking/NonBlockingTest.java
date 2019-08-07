package ru.job4j.nonblocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NonBlockingTest {
    private static final int THREADS_AMOUNT = 2000;
    private static final int CYCLES_AMOUNT = 1_000_000;
    private static AtomicInteger atomicCount = new AtomicInteger();
    private static int simpleCount = 0;

    public static void nonBlockingIncrement() {
        atomicCount.incrementAndGet();
    }

    public static synchronized void simpleIncrement() {
        simpleCount++;
    }

    public static void main(String[] args) throws InterruptedException {

      ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future> list = new ArrayList<>();

      long start = System.currentTimeMillis();
      for(int i = 0; i < THREADS_AMOUNT; i++) {
          Future future = executor.submit(new Runnable() {
              @Override
              public void run() {
                  for (int i = 0; i < CYCLES_AMOUNT; i++) {
                      nonBlockingIncrement();
                  }
              }
          });
          list.add(future);
      }

      for(Future eachTask : list) {
          try {
              eachTask.get();
          } catch (ExecutionException e) {
              e.printStackTrace();
          }
      }
        long end = System.currentTimeMillis();
        System.out.println("result = " + atomicCount.get() + ", NonBlocking algorithm took milliseconds: " + (end - start));


        start = System.currentTimeMillis();
        for(int i = 0; i < THREADS_AMOUNT; i++) {
            Future future = executor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < CYCLES_AMOUNT; i++) {
                        simpleIncrement();
                    }
                }
            });
            list.add(future);
        }

        for(Future eachTask : list) {
            try {
                eachTask.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();

        System.out.println("result = " + atomicCount.get() + ", Simple algorithm took milliseconds: " + (end - start));

        executor.shutdown();
    }
}
