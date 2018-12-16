package ru.job4j.bank;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account was not found");
    }
}
