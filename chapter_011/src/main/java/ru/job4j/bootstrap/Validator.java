package ru.job4j.bootstrap;

import java.util.List;

public interface Validator {


    void addUser(String user);

    String getAllUsers();

    void removeUser(String user);
}
