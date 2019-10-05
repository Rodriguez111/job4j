package ru.job4j.todolist.controller;

import org.json.JSONObject;

public interface Validator {
    void add(String description);

    JSONObject getItems(boolean onlyUndone);
}
