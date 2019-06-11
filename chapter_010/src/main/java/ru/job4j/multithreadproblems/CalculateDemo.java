package ru.job4j.multithreadproblems;

public class CalculateDemo implements Runnable {
    private static int count;

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    public static int getCount() {
        return count;
    }
}
