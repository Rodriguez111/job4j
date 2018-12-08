package ru.job4j.chess.firuges.exceptions;

public class OccupiedWayException extends Exception {
    public OccupiedWayException() {
        super("ThisWayIsOccupied");
    }
}
