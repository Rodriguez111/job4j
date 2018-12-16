package ru.job4j.bank;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Source account has insufficient funds");
    }
}
