package ru.job4j.profiling;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;
import java.util.function.Consumer;

public class TrackerOutOfMemory {
    public static void main(String[] args) {
        Input input = new OutOfMemoryInput(Integer.MAX_VALUE);
        Consumer<String> output = s -> {
        };

        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker, output);
    }

}
