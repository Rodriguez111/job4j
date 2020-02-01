package ru.job4j.threadstart;

public class Wget {

    public static void main(String[] args) {
        Thread downloadThread = new Thread(
                Wget::download
        );
        downloadThread.start();
        join(downloadThread);
        finish();
    }

    private static void download() {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\rLoading : " + i + "%");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void finish() {
        System.out.println();
        System.out.println("Download complete");
    }

}
