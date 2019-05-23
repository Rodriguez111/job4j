package ru.job4j.ticktacktoe.logic;

public class SidesAndDiagonalsCheck implements SidesChecker, DiagonalsChecker {
    private final SidesChecker sidesChecker;
    private final DiagonalsChecker diagonalsChecker;

    public SidesAndDiagonalsCheck(SidesChecker sidesChecker, DiagonalsChecker diagonalsChecker) {
        this.sidesChecker = sidesChecker;
        this.diagonalsChecker = diagonalsChecker;
    }

    @Override
    public boolean checkDiagonals(char character) {
        return this.diagonalsChecker.checkDiagonals(character);
    }

    @Override
    public boolean checkSides(char character) {
        return this.sidesChecker.checkSides(character);
    }

    @Override
    public boolean isWin(char sign1, char sign2) {
        return this.sidesChecker.isWin(sign1, sign2) || this.diagonalsChecker.isWin(sign1, sign2);
    }
}
