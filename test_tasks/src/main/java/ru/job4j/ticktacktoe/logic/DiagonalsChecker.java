package ru.job4j.ticktacktoe.logic;

public interface DiagonalsChecker extends WinChecker {
    boolean checkDiagonals(char character);
}
