package ru.job4j.crudservlet.model;

import ru.job4j.crudservlet.User;

import java.util.List;

public interface Store {
    boolean add(User user);

    void update(int id, User user);

    boolean delete(User user);

    List<User> getAllUsers();

    List<String> getAllRoles();

    List<String> getAllCountries();

    User findById(int id);

    int findIdByField(String fieldValue, String fieldName, String tableName);

    List<String> getListOfValuesByField(String criteriaField, Object criteriaValue, String tableName, String searchField);

}
