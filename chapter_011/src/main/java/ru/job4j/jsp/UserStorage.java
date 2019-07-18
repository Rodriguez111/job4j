package ru.job4j.jsp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {
    private final static UserStorage INSTANCE = new UserStorage();
    private final List<User> users = new CopyOnWriteArrayList<>();

    private UserStorage() {
    }

    public static UserStorage getINSTANCE() {
        return INSTANCE;
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
