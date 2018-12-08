package ru.job4j.chess.firuges.exceptions;

public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException() {
        super("ThisFigureCanNotMoveThisWay");
    }
}
