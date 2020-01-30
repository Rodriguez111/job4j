package ru.job4j.threadstart;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                ConcurrentOutput::run
        );
        Thread second = new Thread(
                ConcurrentOutput::run
        );
        another.start();
        second.start();
        run();
    }

   private static void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
