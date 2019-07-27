package ru.job4j.crudservlet.model;

public interface RolesStore {

    String findRoleById (int roleId);

    int findIdByRole(String role);
}
