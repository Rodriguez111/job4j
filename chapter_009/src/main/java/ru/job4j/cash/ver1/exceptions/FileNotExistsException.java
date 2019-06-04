package ru.job4j.cash.ver1.exceptions;

public class FileNotExistsException extends RuntimeException {
    public FileNotExistsException(String message) {
        super(message);
    }
}
