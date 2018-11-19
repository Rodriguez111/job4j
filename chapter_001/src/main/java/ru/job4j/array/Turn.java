package ru.job4j.array;

/**
 * Inverting array data.
 */

public class Turn {

    /**
     *
     * @param array - array to invert data in.
     * @return - inverted array.
     */
    public int[] back(int[] array) {
        int limit = array.length / 2;

        for (int i = 0; i != limit; i++) {
            int temp = array[i];
            array[i] = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = temp;
        }
        return array;
    }
}
