package ru.job4j.testsrvlet.rep;


import ru.job4j.testsrvlet.models.User;


import java.util.List;

public interface UsersRepository {
    List<User> findAll();
}
