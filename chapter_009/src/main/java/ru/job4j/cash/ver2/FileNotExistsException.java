package ru.job4j.cash.ver2;

public class FileNotExistsException extends RuntimeException {
    public FileNotExistsException(String message) {
        super(message);
    }
}
