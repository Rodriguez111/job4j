package ru.job4j.multithreadproblems;

public class ProblemsDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CalculateDemo());
        Thread thread2 = new Thread(new CalculateDemo());
        Thread thread3 = new Thread(new CalculateDemo());
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(CalculateDemo.getCount());
    }
}
