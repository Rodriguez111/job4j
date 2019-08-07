package ru.job4j.crudservlet.model;

import ru.job4j.crudservlet.User;

import java.util.List;

public interface Store {
    boolean add(User user);

    void update(int id, User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

}
