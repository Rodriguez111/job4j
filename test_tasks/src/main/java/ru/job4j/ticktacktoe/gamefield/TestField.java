package ru.job4j.ticktacktoe.gamefield;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.output.Output;

public class TestField implements Field {


    private final char[][] fieldArray;

    public TestField(char[][] fieldArray) {
        this.fieldArray = fieldArray;
    }


    @Override
    public void printField() {

    }

    @Override
    public boolean addSign(char sign, int x, int y) {
        return false;
    }

    @Override
    public char[][] getField() {
        return this.fieldArray;
    }

    @Override
    public int getEmptyFieldsCount() {
        return 0;
    }

    @Override
    public Pair getEmptyPosition(int elementNumber) {
        return null;
    }

    @Override
    public void removeEmptyPosition(Pair pair) {

    }

    @Override
    public Output getOutput() {
        return null;
    }
}
