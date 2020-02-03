package ru.job4j.threadstart;

public class Wget {

    public static void main(String[] args) {
        Thread downloadThread = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        System.out.print("\rLoading : " + i + "%");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("\rDownload complete");
                }
        );
        downloadThread.start();
    }

}
