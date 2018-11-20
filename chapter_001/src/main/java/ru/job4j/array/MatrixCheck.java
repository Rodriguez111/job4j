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

        int columns = data[0].length;
        int rows = data.length;
            for (int i = 0; i != columns * rows;  i++) {

                if (i /  columns == i % columns) { //main diagonal
                    if (data[i /  columns][i % columns] != firstElement) {
                        result = false;
                        break;
                    }
                }
                if (i /  columns + i % columns == data.length - 1) { //secondary diagonal
                    if (data[i /  columns][i % columns] != firstElement) {
                        result = false;
                        break;
                    }
                }
            }

        return result;
    }
}
