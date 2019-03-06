package ru.job4j.filesearch.argparser.exceptions;

public class CommandIsNotProvidedException extends RuntimeException {
    public CommandIsNotProvidedException(String message) {
        super(message);
    }
}
