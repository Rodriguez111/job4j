package ru.job4j.training.componentscan;

import org.springframework.stereotype.Component;

@Component
public class StorageMemory implements Storage {
    @Override
    public void add(User user) {
        System.out.println("Store to memory");
    }
}
