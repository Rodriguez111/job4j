package ru.job4j.training.mapping;

import org.springframework.stereotype.Component;


public class StorageMemory implements Storage {
    @Override
    public void add(User user) {
        System.out.println("Store to memory");
    }
}
