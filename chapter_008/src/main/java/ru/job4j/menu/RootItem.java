package ru.job4j.menu;


import java.util.Optional;
import java.util.Stack;

public class RootItem {
    private final static String DELIMITER = "=======================";
    private final MenuItem root = new MenuItem("ROOT");

    public RootItem() {
        this.root.setNumber("0.");
    }

    void printMenu() {
        System.out.println(DELIMITER);
        for (MenuItem eachItem : root.getSubMenu()) {

            printItemWithSubItems(eachItem);
        }
        System.out.println(DELIMITER);
    }

    private void printItemWithSubItems(MenuItem item) {
        item.printItem();
        if (item.hasSubMenu()) {
            for (int i = 0; i < item.getSubMenu().size(); i++) {
                printItemWithSubItems(item.getSubMenu().get(i));
            }
        }
    }


    int addItem(String itemNumber, MenuItem subItem) {
        int result = -1;
        Optional<MenuItem> item = getItem(itemNumber);
        if (item.isPresent()) {
            if (item.get().subItemExists(subItem)) {
                result = -2;
            } else {
                item.get().setSubMenu(subItem);
                result = 0;
            }

        }
        return result;
    }


    boolean addAction(String itemNumber, Action action) {
        boolean result = false;
        Optional<MenuItem> item = getItem(itemNumber);
        if (item.isPresent()) {
            item.get().setAction(action);
            result = true;
        }
        return result;
    }

    Optional<MenuItem> getItem(String itemNumber) {
        Optional<MenuItem> result = Optional.empty();
        Stack<MenuItem> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.empty()) {
            MenuItem currentItem = stack.pop();
            if (itemNumber.equals(currentItem.getNumber())) {
                result = Optional.of(currentItem);
            } else {
                for (MenuItem eachItem : currentItem.getSubMenu()) {
                    stack.push(eachItem);
                }

            }
        }
        return result;
    }
}
