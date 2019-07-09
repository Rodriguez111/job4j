package ru.job4j.crudservlet.store;

import ru.job4j.crudservlet.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStore implements Store {
    private final List<User> listOfUsers = new CopyOnWriteArrayList<>();


    private final static UserStore instance = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        user.setId(generateId());
        listOfUsers.add(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (user.equals(listOfUsers.get(i))) {
                listOfUsers.remove(i);
                listOfUsers.add(user);
                break;
            }
        }
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
        int result = 0;
        if(listOfUsers.size() > 0) {
            result =  listOfUsers.get(listOfUsers.size() - 1).getId() + 1;
        }
        return result;
    }
}