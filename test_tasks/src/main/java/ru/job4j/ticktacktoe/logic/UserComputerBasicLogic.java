package ru.job4j.ticktacktoe.logic;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.gamefield.Field;
import ru.job4j.ticktacktoe.input.Input;

import java.util.Random;

public class UserComputerBasicLogic implements BasicLogic {

    private final static char SIGN1 = 'X';
    private final static char SIGN2 = 'O';
    private final WinChecker winChecker;
    private final char[][] field;
    final Field gameField;
    private final Input input;
    boolean gameOver = false;
    boolean user1Begin;
    char user1Sign;
    char user2Sign;


    public UserComputerBasicLogic(Input input, Field gameField, WinChecker winChecker, boolean user1Begin) {
        this.input = input;
        this.field = gameField.getField();
        this.gameField = gameField;
        this.winChecker = winChecker;
        this.user1Begin = user1Begin;
        initSigns();
    }

    private void initSigns() {
        if (user1Begin) {
            user1Sign = SIGN1;
            user2Sign = SIGN2;
        } else {
            user1Sign = SIGN2;
            user2Sign = SIGN1;
        }
    }

    public boolean canMove() {
        boolean result = false;
        if (isWin()) {
            this.gameOver = true;
        } else {
            for (int y = 0; y < field[0].length; y++) {
                this.gameOver = true;
                for (int x = 0; x < field.length; x++) {
                    if (field[x][y] == '.') {
                        this.gameOver = false;
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }


    public boolean isWin() {
       return this.winChecker.isWin(SIGN1, SIGN2);
    }

    public boolean move(char sign, int x, int y) {
        boolean result = false;
        if (gameField.addSign(sign, x, y)) {
            Pair<Integer, Integer> field = new Pair<>(x, y);
            gameField.removeEmptyPosition(field);
            result = true;
        }
        return result;
    }

    private Pair<Integer, Integer> getRandomCoordinates() {
        Random randomGenerator = new Random();
        int randomField = randomGenerator.nextInt(this.gameField.getEmptyFieldsCount());
        return this.gameField.getEmptyPosition(randomField);
    }

    public Pair<Integer, Integer> getUserCoordinates() {
        int x = this.input.inputCoordinate("Input x");
        int y = this.input.inputCoordinate("Input y");
        return new Pair<>(x, y);
    }

    public void playGame() {
        if (!user1Begin) {
            Pair<Integer, Integer> coordinates = getRandomCoordinates();
            move(user2Sign, coordinates.getKey(), coordinates.getValue());
        }
        while (!gameOver) {
            gameField.printField();
            if (!canMove()) {
                break;
            }
            boolean userMoveSuccess = false;
            while (!userMoveSuccess) {
                Pair<Integer, Integer> coordinates = getUserCoordinates();
                userMoveSuccess = move(user1Sign, coordinates.getKey(), coordinates.getValue());
            }

            gameField.printField();
            if (!canMove()) {
                break;
            }
            Pair<Integer, Integer> coordinates = getRandomCoordinates();
            move(user2Sign, coordinates.getKey(), coordinates.getValue());
        }
    }

    @Override
    public void printWhoMoves(char sign) {
        if(!gameOver) {
          gameField.getOutput().printWhoMoves(sign);
        }

    }

    public Field getGameField() {
        return gameField;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isUser1Begin() {
        return user1Begin;
    }

    public char getUser1Sign() {
        return user1Sign;
    }

    public char getUser2Sign() {
        return user2Sign;
    }
}