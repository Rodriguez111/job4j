package ru.job4j.bank;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User was not found");
    }
}
