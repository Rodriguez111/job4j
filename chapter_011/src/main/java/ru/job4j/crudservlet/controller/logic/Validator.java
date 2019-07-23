package ru.job4j.crudservlet.controller.logic;

import ru.job4j.crudservlet.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Validator {
    boolean add(HttpServletRequest request);

    boolean update(HttpServletRequest request);

    boolean delete(HttpServletRequest request);

    List<User> findAll();

    User findById(int id);
}
