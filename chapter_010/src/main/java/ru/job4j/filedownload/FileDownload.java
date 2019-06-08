package ru.job4j.filedownload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload implements Runnable {

    private final static String DOWNLOAD_PATH = System.getProperty("java.io.tmpdir");
    private final String fileURL;
    private final int speedLimit;

    public FileDownload(String fileURL, int speedLimit) {
        this.fileURL = fileURL;
        this.speedLimit = speedLimit;
    }

    @Override
    public void run() {
        downloadFile();
    }

    public void downloadFile() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(fileURL).openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            File file = new File(DOWNLOAD_PATH + getFileName());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            long startTime = System.currentTimeMillis();
            int read = bufferedInputStream.read(buffer, 0, bufferSize);
            long kbDownloaded = 0;
            while (read > 0) {
                kbDownloaded =  kbDownloaded + (read / 1024);
                long currentTime = System.currentTimeMillis();
                long timeDiff = currentTime - startTime;
                if (timeDiff >= 100) {
                    delay(kbDownloaded);
                    kbDownloaded = 0;
                    startTime = currentTime;
                }
                fileOutputStream.write(buffer, 0, read);
                read = bufferedInputStream.read(buffer, 0, bufferSize);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delay(long portion) {
        if (portion > speedLimit / 10) {
            int delayTime = 100 * (int) (portion - speedLimit / 10) / (speedLimit / 10);
            sleep(delayTime);
        }
    }

    private String getFileName() {
        return fileURL.substring(fileURL.lastIndexOf("/") + 1);
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        String url = "https://www.rarlab.com/rar/winrar-x64-571ru.exe";
        //String url = "https://download-cf.jetbrains.com/idea/ideaIU-2019.1.3.exe";
        FileDownload fileDownload = new FileDownload(url, 200);
        Thread thread = new Thread(fileDownload);
        thread.start();
    }
}
