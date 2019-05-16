package ru.job4j.generator.exceptions;

public class NotEnoughKeysForProcessingException extends RuntimeException {
    public NotEnoughKeysForProcessingException(String message) {
        super(message);
    }
}
