package ru.job4j.crudservlet.controller.logic;

import ru.job4j.crudservlet.AdvancedUser;
import ru.job4j.crudservlet.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ValidatorWithRole extends Validator  {
    List<AdvancedUser> findAllAdvUsers();

    AdvancedUser findAdvUserById(int id);

    String isCredentialWithRole(String login, String password);

}
