package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private static final String SPACE = " ";

    private String name;

    private String number;

    private int offset;

    private Action action;

    private List<MenuItem> subMenu = new ArrayList<>();

    public MenuItem(String name) {
        this.name = name;
        this.number = "1.";
    }

    public void setSubMenu(MenuItem child) {
        String childrenNumber;
        childrenNumber = this.number + "" + (subMenu.size() + 1) + ".";
        child.setNumber(childrenNumber);
        child.setOffset(this.offset + 1);
        this.subMenu.add(child);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<MenuItem> getSubMenu() {
        return this.subMenu;
    }

    public void printItem() {
        System.out.println(generateOffset() + this.number + SPACE + this.name);
    }

    public boolean hasSubMenu() {
        return subMenu.size() > 0;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private String generateOffset() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.offset; i++) {
            sb.append(SPACE);
        }
        return sb.toString();
    }

    public String getNumber() {
        return number;
    }

    public Action getAction() {
        return action;
    }
}
