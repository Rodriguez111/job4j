package ru.job4j.menu;

import java.util.Optional;

public class Menu {
    private final Input input;
    private final RootItem root;
    private final MenuInitiator menuInitiator;


    public Menu(Input input) {
        this.input = input;
        this.root = new RootItem();
        this.menuInitiator = new MenuInitiator(this);
        start();
    }

    public void setItem(MenuItem item) {
        root.addItem(item);
    }

    private void print() {
        root.printMenu();
    }

    private Optional<Action> getAction(String itemNumber) {
        return root.getAction(itemNumber);
    }

    public static void main(String[] args) {
        Menu menu = new Menu(new ConsoleInput());
    }

    private void start() {
        while (menuInitiator.isRunning()) {
            print();
            String answer = input.askUser("Select item: ");
            Optional<Action> action = getAction(answer);
            if (action.isEmpty()) {
                System.out.println("Incorrect item number, please try again");
                continue;
            }
            Action act = action.get();
            act.run();
        }
    }
}
