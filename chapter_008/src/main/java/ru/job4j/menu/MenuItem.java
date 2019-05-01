package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuItem {
    private static final String SPACE = " ";

    private String name;

    private String number;

    private int offset;

    private Action action = () -> {
        System.out.println("No action is associated with this item.");
    };

    private List<MenuItem> subMenu = new ArrayList<>();

    public MenuItem(String name) {
        this.name = name;
        this.number = "1.";
    }

    void setSubMenu(MenuItem child) {
        String currentNumber = this.number;
        if (currentNumber.equals("0.")) {
            currentNumber = "";
        }
        String childrenNumber;
        childrenNumber = currentNumber + "" + (subMenu.size() + 1) + ".";
        child.setNumber(childrenNumber);
        if (!this.number.equals("0.")) {
            child.setOffset(this.offset + 1);
        }
        this.subMenu.add(child);
    }

    void setNumber(String number) {
        this.number = number;
    }

    private void setOffset(int offset) {
        this.offset = offset;
    }

    List<MenuItem> getSubMenu() {
        return this.subMenu;
    }

    void printItem() {
        System.out.println(generateOffset() + this.number + SPACE + this.name);
    }

    boolean hasSubMenu() {
        return subMenu.size() > 0;
    }

    boolean subItemExists(MenuItem rootItem) {
        return this.subMenu.contains(rootItem);
    }

    void setAction(Action action) {
        this.action = action;
    }

    private String generateOffset() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.offset; i++) {
            sb.append(SPACE);
        }
        return sb.toString();
    }

    String getNumber() {
        return number;
    }

    Action getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
