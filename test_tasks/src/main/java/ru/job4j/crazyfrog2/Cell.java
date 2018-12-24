package ru.job4j.crazyfrog2;

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


}
