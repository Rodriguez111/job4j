package ru.job4j.threadstart;


public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                ThreadState::run
        );
        Thread second = new Thread(
                ThreadState::run
        );
        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED){

        };
        System.out.println("Job is done");
    }

    private static void run() {
        System.out.println(Thread.currentThread().getName());
    }






}
