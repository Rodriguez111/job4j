package ru.job4j.ticktacktoe.output;

public interface Output {
    void printOutOfFieldError();

    void printFieldEngagedError();

    void printField(char[][] field);

    void printWhoMoves(char sign);
}
