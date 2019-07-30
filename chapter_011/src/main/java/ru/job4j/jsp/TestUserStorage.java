package ru.job4j.jsp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestUserStorage {
    private final static TestUserStorage INSTANCE = new TestUserStorage();
    private final List<TestUser> users = new CopyOnWriteArrayList<>();

    private TestUserStorage() {
    }

    public static TestUserStorage getINSTANCE() {
        return INSTANCE;
    }

    public void add(TestUser user) {
        users.add(user);
    }

    public List<TestUser> getUsers() {
        return users;
    }
}
