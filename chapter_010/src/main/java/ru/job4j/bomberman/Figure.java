package ru.job4j.bomberman;

public interface Figure {
    Cell currentPosition();

    void setCurrentPosition(Cell currentPosition);

    Cell getCurrentPosition();

    String getName();

}
