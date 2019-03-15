package ru.job4j.tracker;

import java.util.function.Consumer;

public interface UserAction {
    int key();
    void execute(Input input, ITracker iTracker, Consumer<String> output);
    String info();
}
