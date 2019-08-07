package ru.job4j.crudservlet.model;

import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;

import java.util.List;

public interface RolesStore extends Store {

    String findRoleById (int roleId);

    int findIdByRole(String role);

    boolean userExists(String login);

    AdvancedUser findAdvUserById(int id);

    boolean addAdvUser(AdvancedUser advancedUser);

    void updateAdvUser(int id, AdvancedUser advancedUser);

}
