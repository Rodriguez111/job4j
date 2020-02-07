package ru.job4j.threadstop;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] array = {'\\', '|','/','-'};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: -" + array[count] + "-");
            count++;
            if (count == 4) {
                count = 0;
            }
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(2000);
        progress.interrupt();
    }

}
