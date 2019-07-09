package ru.job4j.crudservlet.store;

import ru.job4j.crudservlet.User;

import java.util.List;

public interface Store {
    void add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);
}
