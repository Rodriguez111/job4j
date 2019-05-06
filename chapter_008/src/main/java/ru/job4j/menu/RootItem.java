package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RootItem {
    private final static String DELIMITER = "=======================";
    private final List<MenuItem> root = new ArrayList<>();
    private static RootItem rootItem;
    private int count;


    public void printMenu() {
        System.out.println(DELIMITER);
        for (MenuItem eachItem : root) {
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

    public void addItem(MenuItem item) {
        item.setNumber(++count + ".");
        root.add(item);
    }

    public Optional<Action> getAction(String itemNumber) {
        Optional<Action> result = Optional.empty();
        for (MenuItem eachItem : root) {
            if (result.isPresent()) {
                break;
            }
            result = search(eachItem, itemNumber);
        }
        return result;
    }

    private Optional<Action> search(MenuItem item, String itemNumber) {
        Optional<Action> result = Optional.empty();
        if (itemNumber.equals(item.getNumber())) {
            return Optional.of(item.getAction());
        }
        if (item.hasSubMenu()) {
            for (int i = 0; i < item.getSubMenu().size(); i++) {
                if (result.isPresent()) {
                    break;
                }
                result = search(item.getSubMenu().get(i), itemNumber);
            }
        }
        return result;
    }
}
