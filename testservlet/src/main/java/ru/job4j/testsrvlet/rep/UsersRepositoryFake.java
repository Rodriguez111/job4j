package ru.job4j.testsrvlet.rep;

import ru.job4j.testsrvlet.models.User;


import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsersRepositoryFake implements UsersRepository {
    private final static UsersRepositoryFake INSTANCE = new UsersRepositoryFake();




    //private List<User> users = new CopyOnWriteArrayList<>();

    private UsersRepositoryFake() {
        User user1 = new User("User1", "password123", LocalDate.parse("1985.10.01"));
        User user2 = new User("User2", "password456", LocalDate.parse("1985.10.02"));
        User user3 = new User("User3", "password789", LocalDate.parse("1985.10.03"));
//        this.users.add(user1);
//        this.users.add(user2);
//        this.users.add(user3);
    }

    public static UsersRepositoryFake getInstance() {
        return INSTANCE;
    }


        public List<User> findAll() {
        return null;
    }
}
