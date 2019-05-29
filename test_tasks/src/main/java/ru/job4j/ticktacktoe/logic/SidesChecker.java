package ru.job4j.ticktacktoe.logic;

public interface SidesChecker extends WinChecker {
    boolean checkSides(char character);
}
