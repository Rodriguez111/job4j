package ru.job4j.training.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class UserStorage {
    private final Storage storage;


    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }
}
