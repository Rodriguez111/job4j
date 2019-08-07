package ru.job4j.crudservlet;

public enum Roles {
    ADMINISTRATOR("administrator"),
    USER("user");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
