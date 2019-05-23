package ru.job4j.ticktacktoe.logic;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.gamefield.Field;
import ru.job4j.ticktacktoe.input.Input;

public class UserUserBasicLogic implements BasicLogic {

    private final BasicLogic basicLogic;

    public UserUserBasicLogic(BasicLogic basicLogic) {
        this.basicLogic = basicLogic;
    }


   private boolean makeMove(char sign) {
        Pair<Integer, Integer> coordinates = getUserCoordinates();
        return move(sign, coordinates.getKey(), coordinates.getValue());
    }

    @Override
    public boolean isWin() {
        return this.basicLogic.isWin();
    }

    @Override
    public boolean move(char sign, int x, int y) {
        return this.basicLogic.move(sign, x, y);
    }

    @Override
    public Pair<Integer, Integer> getUserCoordinates() {
        return this.basicLogic.getUserCoordinates();
    }

    @Override
    public void playGame() {
        if (!isUser1Begin()) {
            getGameField().printField();
            printWhoMoves(getUser2Sign());
            boolean userMoveSuccess = false;
            while (!userMoveSuccess) {
                userMoveSuccess = makeMove(getUser2Sign());
            }
        }
        while (!isGameOver()) {
            getGameField().printField();
            if (!canMove()) {
                break;
            }
            printWhoMoves(getUser1Sign());
            boolean userMoveSuccess = false;
            while (!userMoveSuccess) {
                userMoveSuccess = makeMove(getUser1Sign());
            }

            getGameField().printField();
            if (!canMove()) {
                break;
            }
            printWhoMoves(getUser2Sign());
            userMoveSuccess = false;
            while (!userMoveSuccess) {
                userMoveSuccess = makeMove(getUser2Sign());
            }
        }
    }

    @Override
    public boolean canMove() {
        return this.basicLogic.canMove();
    }

    @Override
    public Field getGameField() {
        return this.basicLogic.getGameField();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isUser1Begin() {
        return this.basicLogic.isUser1Begin();
    }

    @Override
    public char getUser1Sign() {
        return this.basicLogic.getUser1Sign();
    }

    @Override
    public char getUser2Sign() {
        return this.basicLogic.getUser2Sign();
    }

    @Override
    public void printWhoMoves(char sign) {
        this.basicLogic.printWhoMoves(sign);
    }
}
