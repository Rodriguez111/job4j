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

        for (int i = 1; i < data.length; i++) {
            if (data[0][0] != data[i][i]) {
                result = false;
                break;
            }
            if (data[0][data.length - 1] != data[i][data.length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
