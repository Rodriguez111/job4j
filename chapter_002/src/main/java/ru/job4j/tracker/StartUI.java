package ru.job4j.tracker;

import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Tracker tracker;


    public StartUI() {
        this.input = new ValidateInput(new ConsoleInput());
        this.tracker = new Tracker();
    }

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        init();
    }


    /**
     * initiating main logic.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        do {
            Consumer<String> consumer = System.out::println;
            menuTracker.showMenu(consumer);
            int key = input.ask("Input menu item: ", menuTracker.availableKeys());
            menuTracker.selectKey(key - 1);
        } while (menuTracker.isRunning());
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI().init();
    }

}
