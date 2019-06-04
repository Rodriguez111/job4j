package ru.job4j.cash.ver1.exceptions;

public class CashReadFailedException extends RuntimeException {
    public CashReadFailedException(String message) {
        super(message);
    }
}
