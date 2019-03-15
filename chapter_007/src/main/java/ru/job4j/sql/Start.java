package ru.job4j.sql;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.StartUI;
import ru.job4j.trackersql.TrackerSQL;

import java.util.function.Consumer;

public class Start extends StartUI {

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        ITracker iTracker = new TrackerSQL();
        Consumer<String> output = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        new StartUI(input, iTracker, output);
    }
}