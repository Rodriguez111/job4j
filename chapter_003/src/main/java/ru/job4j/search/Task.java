package ru.job4j.search;

public class Task {
    private String description;
    private int priority;

    public Task(String desc, int priority) {
        this.description = desc;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
