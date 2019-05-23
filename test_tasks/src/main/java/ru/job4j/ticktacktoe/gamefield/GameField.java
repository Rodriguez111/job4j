package ru.job4j.ticktacktoe.gamefield;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.output.Output;

import java.util.ArrayList;
import java.util.List;

public class GameField implements Field {
    private final char[][] field;
    private final Output output;
    private List<Pair<Integer, Integer>> emptyPositions = new ArrayList<>();

    public GameField(int fieldSize, Output output) {
        this.field = new char[fieldSize][fieldSize];
        initField();
        this.output = output;
    }

    public void initField() {
        for (int y = 0; y < this.field[0].length; y++) {
            for (int x = 0; x < this.field.length; x++) {
                this.field[x][y] = '.';
                this.emptyPositions.add(new Pair<Integer, Integer>(x, y));
            }
        }
    }

    @Override
    public void printField() {
        this.output.printField(this.field);
    }

    public boolean addSign(char sign, int x, int y) {
        boolean result = false;
        if (checkFieldBounds(x, y) && checkFieldVacancy(x, y)) {
            this.field[x][y] = sign;
            result = true;
        }
        return result;
    }

    private boolean checkFieldVacancy(int x, int y) {
        boolean result = this.field[x][y] == '.';
        if (!result) {
            this.output.printFieldEngagedError();
        }
        return result;
    }

    private boolean checkFieldBounds(int x, int y) {
        boolean result = x >= 0 && x < this.field.length && y >= 0 && y < this.field[0].length;
        if (!result) {
            this.output.printOutOfFieldError();
        }
        return result;
    }

    @Override
    public char[][] getField() {
        return this.field;
    }

    @Override
    public int getEmptyFieldsCount() {
        return this.emptyPositions.size();
    }

    @Override
    public Pair getEmptyPosition(int elementNumber) {
        Pair result = this.emptyPositions.get(elementNumber);
        this.emptyPositions.remove(elementNumber);
        return result;
    }

    @Override
    public void removeEmptyPosition(Pair pair) {
        this.emptyPositions.remove(pair);
    }

    public Output getOutput() {
        return output;
    }
}
