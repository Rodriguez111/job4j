package ru.job4j.ticktacktoe.logic;

public class SidesCheck implements SidesChecker {
    private final char[][] field;

    public SidesCheck(char[][] field) {
        this.field = field;
    }

    @Override
    public boolean checkSides(char character) {
        boolean resultX = true;
        boolean resultY = true;
        for (int y = 0; y < field.length; y++) {
            resultX = true;
            resultY = true;
            for (int x = 0; x < field[0].length; x++) {
                if (character != field[y][x]) {
                    resultX = false;
                }
                if (character != field[x][y]) {
                    resultY = false;
                }
            }
            if (resultX || resultY) {
                break;
            }
        }
        return resultX || resultY;
    }

    @Override
    public boolean isWin(char sign1, char sign2) {
        boolean result = false;
        if (checkSides(sign1)) {
            System.out.println(sign1 + " Won!");
            result = true;
        }
        if (checkSides(sign2)) {
            System.out.println(sign2 + " Won!");
            result = true;
        }
        return result;
    }
}
