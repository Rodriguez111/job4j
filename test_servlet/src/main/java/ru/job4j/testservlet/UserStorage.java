package ru.job4j.testservlet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {
    private final static UserStorage INSTANCE = new UserStorage();
    private final List<User> users = new CopyOnWriteArrayList<>();
    private static int count;

    private UserStorage() {
        users.add(new User(generateId(), "root", "root", "root@mail.com"));
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

    public boolean isCredential(String login, String password) {
        boolean result = false;
        for (User eachUser : users) {
            if (eachUser.getLogin().equals(login) && eachUser.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private String generateId() {
        int result = 1;
        if (users.size() > 0) {
            result = Integer.parseInt(users.get(users.size() - 1).getId()) + 1;
        }
        return String.valueOf(result);
    }

}
