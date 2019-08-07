package ru.job4j.crudservlet.controller.logic;

import ru.job4j.crudservlet.AdvancedUser;

import java.util.List;

public interface ValidatorWithRole {
    List<AdvancedUser> findAllAdvUsers();

    AdvancedUser findAdvUserById(int id);

    String isCredentialWithRole(String login, String password);
}
