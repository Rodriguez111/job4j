package ru.job4j.crazyfrogtwo;

public class Cell {
    private int sector;
    private int segment;



    private int value;

    private Cell nearLeft;
    private Cell nearRight;
    private Cell middleLeft;
    private Cell middleRight;
    private Cell farAway;


    private boolean hasNearLeft;
    private boolean hasNearRight;
    private boolean hasMiddleLeft;
    private boolean hasMiddleRight;
    private boolean hasFarAway;

    public Cell(int sector, int segment) {
        this.sector = sector;
        this.segment = segment;
        this.hasNearLeft = true;
        this.hasNearRight = true;
        this.hasMiddleLeft = true;
        this.hasMiddleRight = true;
        this.hasFarAway = true;


    }

    public void setNearLeft(Cell nearLeft) {
        this.nearLeft = nearLeft;
    }

    public void setNearRight(Cell nearRight) {
        this.nearRight = nearRight;
    }

    public void setMiddleLeft(Cell middleLeft) {
        this.middleLeft = middleLeft;
    }

    public void setMiddleRight(Cell middleRight) {
        this.middleRight = middleRight;
    }

    public void setFarAway(Cell farAway) {
        this.farAway = farAway;
    }

    public void setHasNearLeft(boolean hasNearLeft) {
        this.hasNearLeft = hasNearLeft;
    }

    public void setHasNearRight(boolean hasNearRight) {
        this.hasNearRight = hasNearRight;
    }

    public void setHasMiddleLeft(boolean hasMiddleLeft) {
        this.hasMiddleLeft = hasMiddleLeft;
    }

    public void setHasMiddleRight(boolean hasMiddleRight) {
        this.hasMiddleRight = hasMiddleRight;
    }

    public void setHasFarAway(boolean hasFarAway) {
        this.hasFarAway = hasFarAway;
    }



    public int getSector() {
        return sector;
    }

    public int getSegment() {
        return segment;
    }

    public boolean hasNearLeft() {
        return hasNearLeft;
    }

    public boolean hasNearRight() {
        return hasNearRight;
    }

    public boolean hasMiddleLeft() {
        return hasMiddleLeft;
    }

    public boolean hasMiddleRight() {
        return hasMiddleRight;
    }

    public boolean hasFarAway() {
        return hasFarAway;
    }

    public Cell getNearLeft() {
        return nearLeft;
    }

    public Cell getNearRight() {
        return nearRight;
    }

    public Cell getMiddleLeft() {
        return middleLeft;
    }

    public Cell getMiddleRight() {
        return middleRight;
    }

    public Cell getFarAway() {
        return farAway;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "Cell{"
                + "sector="
                + sector
                + ", segment="
                + segment
                + ", value="
                + value
                + '}';
    }
}
