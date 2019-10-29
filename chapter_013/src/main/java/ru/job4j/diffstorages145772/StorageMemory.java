package ru.job4j.diffstorages145772;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class StorageMemory implements Storage {
    private final static ConcurrentHashMap<Integer, User> STORAGE = new ConcurrentHashMap<>();

    private final static AtomicInteger COUNTER = new AtomicInteger();

    @Override
    public String add(User user) {
        String result = "Пользователь с таким логином уже существует.";
        if (!userExists(user)) {
            STORAGE.put(COUNTER.incrementAndGet(), user);
            result = "OK";
        }
        return result;
    }

    @Override
    public boolean userExists(User user) {
        boolean result = false;
        for (User eachUser : STORAGE.values()) {
            if (user.equals(eachUser)) {
                result = true;
            }
        }
        return result;
    }
}
