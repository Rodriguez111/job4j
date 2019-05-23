package ru.job4j.ticktacktoe.logic;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.gamefield.Field;

public interface BasicLogic {

    boolean isWin();

    boolean move(char sign, int x, int y);

    Pair<Integer, Integer> getUserCoordinates();

    boolean canMove();

    void playGame();

    Field getGameField();

    boolean isGameOver();

    boolean isUser1Begin();

    char getUser1Sign();

    char getUser2Sign();

    void printWhoMoves(char sign);

}
