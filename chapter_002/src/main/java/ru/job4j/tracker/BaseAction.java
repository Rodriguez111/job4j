package ru.job4j.tracker;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {
    private int key;
    private String name;

    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public abstract void execute(Input input, Tracker tracker, Consumer<String> output);

    @Override
    public String info() {
        return String.format("%s %s", this.key(), this.name);
    }
}
