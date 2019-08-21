package ru.job4j.bootstrap;

import java.util.List;

public interface Storage {

    void addUser(User user);

    List<User> getAllUsers();

    void removeUser(User user);
}
