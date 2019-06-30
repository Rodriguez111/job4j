package ru.job4j.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private void emailTo(User user) {
        StringBuilder sb = new StringBuilder();
        String subject = sb.append("Notification ")
                .append(user.getUserName())
                .append(" to email ")
                .append(user.getEmail()).toString();
        sb.setLength(0);
        String body = sb.append("Add a new event to ")
                .append(user.getEmail()).toString();
        send(subject, body, user.getEmail());
    }

    private void send(String subject, String body, String email) {

    }

    public void submit(User user) {
        pool.submit(() -> emailTo(user));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
