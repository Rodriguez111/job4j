package ru.job4j.crudservlet.store;

import ru.job4j.crudservlet.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStore implements Store {
    private final List<User> listOfUsers = new CopyOnWriteArrayList<>();
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final static UserStore INSTANCE = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        user.setId(generateId());
        listOfUsers.add(user);
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(User user) {
        listOfUsers.remove(user);
    }

    @Override
    public List<User> findAll() {
        return this.listOfUsers;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (id == listOfUsers.get(i).getId()) {
                result = listOfUsers.get(i);
                break;
            }
        }
        return result;
    }

    private int generateId() {
        return COUNT.incrementAndGet();
    }
}