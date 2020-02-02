package ru.job4j.threadstart;

import java.util.ArrayList;
import java.util.List;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                ThreadState::run
        );
        Thread second = new Thread(
                ThreadState::run
        );
        printThreadName(first);
        first.start();
        printThreadState(first);
        printThreadName(second);
        second.start();
        printThreadState(second);
        List<Thread> threads = new ArrayList<>();
        threads.add(first);
        threads.add(second);
        waitAllFinish(threads);
        System.out.println("Job is done");

    }

    private static void printThreadState(Thread thread) {
        while (thread.getState() != Thread.State.TERMINATED) {
            System.out.println(thread.getName() + " - " + thread.getState());
        }
    }

    private static void printThreadName(Thread thread) {
            System.out.println(thread.getName() + " - " + thread.getState());
    }

    private static void run() {
        System.out.println(Thread.currentThread().getName());
    }

    private static void waitAllFinish(List<Thread> threads) {
        boolean result = false;
        do {
            if (threads.size() == 0) {
                result = true;
            }
            for (Thread eachThread : threads) {
                if (eachThread.getState() == Thread.State.TERMINATED) {
                    threads.remove(eachThread);
                    break;
                }
            }
        }while (!result);
    }





}
