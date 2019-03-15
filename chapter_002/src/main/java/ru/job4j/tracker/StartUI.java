package ru.job4j.tracker;



import java.util.function.Consumer;



public class StartUI {
    private final Input input;
    private final ITracker iTracker;
    private final Consumer<String> output;


    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.iTracker = tracker;
        this.output = output;
        init();
    }

    public StartUI() {
        this.input = new ValidateInput(new ConsoleInput());
        this.iTracker = new Tracker();
        this.output = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        init();
    }


    /**
     * initiating main logic.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.iTracker, this.output);
        do {
            menuTracker.showMenu(output);
            int key = input.ask("Input menu item: ", menuTracker.availableKeys());
            menuTracker.selectKey(key - 1);
        } while (menuTracker.isRunning());
    }

    public static void main(String[] args) {
//        Input input = new ConsoleInput();
//        ITracker iTracker = new TrackerSQL();
//        Consumer<String> output = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
//        new StartUI(input, iTracker, output);

        new StartUI();
    }

}
