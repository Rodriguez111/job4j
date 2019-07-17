package ru.job4j.bomberman;

public class Hero implements Figure {

    private Cell currentPosition;
    private final String name;

    public Hero(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Cell currentPosition() {
        return this.currentPosition;
    }

    @Override
    public void setCurrentPosition(Cell currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public Cell getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public String toString() {
        return "Hero{"
                + "x=" + currentPosition.getX() + " y=" + currentPosition.getY()
                + ", name='" + name + '\''
                + '}';
    }
}
