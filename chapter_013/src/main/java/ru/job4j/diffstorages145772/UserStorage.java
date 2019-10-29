package ru.job4j.diffstorages145772;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {
    private final Storage storage;

    @Autowired
    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public String add(User user) {
        return this.storage.add(user);
    }
}
