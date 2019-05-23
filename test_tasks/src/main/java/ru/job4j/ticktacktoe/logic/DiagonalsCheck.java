package ru.job4j.ticktacktoe.logic;

public class DiagonalsCheck implements DiagonalsChecker {
    private final char[][] field;

    public DiagonalsCheck(char[][] field) {
        this.field = field;
    }

    @Override
    public boolean checkDiagonals(char character) {
        boolean result = true;
        for (int i = 0; i < field.length; i++) {
            if (character != field[i][i]) {
                result = false;
                break;
            }
        }
        if (!result) {
            result = true;
            for (int i = 0; i < field.length; i++) {
                if (character != field[i][field.length - 1 - i]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean isWin(char sign1, char sign2) {
        boolean result = false;
        if (checkDiagonals(sign1)) {
            System.out.println(sign1 + " Won!");
            result = true;
        }
        if (checkDiagonals(sign2)) {
            System.out.println(sign2 + " Won!");
            result = true;
        }
        return result;
    }


}
