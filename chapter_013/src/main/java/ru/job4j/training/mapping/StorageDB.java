package ru.job4j.training.mapping;


public class StorageDB implements Storage {
    @Override
    public void add(User user) {
        System.out.println("Storage to DB");
    }
}
