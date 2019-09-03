package ru.job4j.bootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStorage implements Storage {

    private final static AtomicInteger COUNT = new AtomicInteger();

    private final static ConcurrentHashMap<Integer, User> MAP = new ConcurrentHashMap();

    private final static UserStorage INSTANCE = new UserStorage();

    private UserStorage() {
    }

    public static UserStorage getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void addUser(User user) {
        MAP.put(generateId(), user);
    }

    @Override
    public List<User> getAllUsers() {
      return new ArrayList<>(MAP.values());
    }

    @Override
    public void removeUser(User user) {

    }

    private int generateId() {
        return COUNT.incrementAndGet();
    }
}
