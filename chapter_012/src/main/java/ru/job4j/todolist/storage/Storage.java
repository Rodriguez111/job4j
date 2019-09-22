package ru.job4j.todolist.storage;

import ru.job4j.todolist.models.Item;

import java.util.List;

public interface Storage {
    List<Item> getAllItems(boolean onlyUndone);

    void addItem(Item item);
}
