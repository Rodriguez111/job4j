package ru.job4j.ticktacktoe.gamefield;

import javafx.util.Pair;
import ru.job4j.ticktacktoe.output.Output;

public interface Field {
    void printField();

    boolean addSign(char sign, int x, int y);

    char[][] getField();

    int getEmptyFieldsCount();

    Pair getEmptyPosition(int elementNumber);

    void removeEmptyPosition(Pair pair);

    Output getOutput();
}
