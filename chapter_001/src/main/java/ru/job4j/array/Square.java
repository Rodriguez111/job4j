package ru.job4j.array;

/**
 * Filling array with data.
 */

public class Square {

    /**
     * Returns new array filled with squared numbers.
     * @param bound - array length.
     * @return - filled array.
     */
    public int[] calculate(int bound) {
        int[] calculate = new int[bound];
        for (int index = 0; index != bound; index++) {
            calculate[index] = (int) Math.pow(index + 1, 2);
        }
        return calculate;
    }
}
