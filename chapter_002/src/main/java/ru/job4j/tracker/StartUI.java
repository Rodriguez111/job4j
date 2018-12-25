package ru.job4j.tracker;

import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;


    public StartUI() {
        this.input = new ValidateInput(new ConsoleInput());
        this.tracker = new Tracker();
        this.output = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };
    }

    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
        init();
    }


    /**
     * initiating main logic.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker, this.output);
        do {
            menuTracker.showMenu(output);
            int key = input.ask("Input menu item: ", menuTracker.availableKeys());
            menuTracker.selectKey(key - 1);
        } while (menuTracker.isRunning());
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI().init();
    }

}
