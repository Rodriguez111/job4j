package ru.job4j.menu;

import java.util.Optional;

public class Menu {
    private final Input input;
    private final RootItem root = new RootItem();
    private final MenuInitiator menuInitiator;

    public Menu(Input input) {
        this.input = input;
        this.menuInitiator = new MenuInitiator(this);
        start();
    }

    private void print() {
        root.printMenu();
    }

    private Optional<Action> getAction(String itemNumber) {
        Optional<Action> result = Optional.empty();
        Optional<MenuItem> item = root.getItem(itemNumber);
        if (item.isPresent()) {
            result = Optional.of(item.get().getAction());
        }
        return result;
    }

    void addItem(String itemNumber, MenuItem subItem) {
        int result = root.addItem(itemNumber, subItem);
        if (result == -1) {
            itemNotFoundMsg(itemNumber);
        } else if (result == -2) {
            subItemExistsMsg(itemNumber, subItem.getName());
        }
    }

    void addAction(String itemNumber, Action action) {
        if (!root.addAction(itemNumber, action)) {
            itemNotFoundMsg(itemNumber);
        }
    }

    private void itemNotFoundMsg(String itemNumber) {
        System.out.println("Item with number \"" + itemNumber + "\" is not exists");
    }

    private void subItemExistsMsg(String itemNumber, String subItemName) {
        System.out.println("Sub item \"" + subItemName + "\" already exists in item \"" + itemNumber + "\"");
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

    public static void main(String[] args) {
        Menu menu = new Menu(new ConsoleInput());
    }

}
