package ru.job4j.array;

/**
 * Checking matrix diagonals.
 */

public class MatrixCheck {

    /**
     *
     * @param data - matrix to check.
     * @return - check result.
     */

    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean firstElement = data[0][0];
        for (int column = 0; column != data.length;  column++) {
            for (int row = 0; row != data[0].length;  row++) {

                if (row == column) { //main diagonal
                    if (data[column][row] != firstElement) {
                        result = false;
                        break;
                    }
                }
                if (column + row == data.length - 1) { //secondary diagonal
                    if (data[column][row] != firstElement) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
