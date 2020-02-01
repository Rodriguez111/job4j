package ru.job4j.threadstart;

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
        join(first);
        join(second);
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

    private static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
