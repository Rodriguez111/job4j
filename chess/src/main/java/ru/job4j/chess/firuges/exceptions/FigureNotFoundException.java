package ru.job4j.chess.firuges.exceptions;

public class FigureNotFoundException extends Exception {
    public FigureNotFoundException() {
        super("FigureNotFoundInSourceCell");
    }
}
