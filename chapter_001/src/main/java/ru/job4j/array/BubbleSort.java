package ru.job4j.array;

/**
 * Sorting array.
 */

public class BubbleSort {

    /**
     * Sorting array ascending.
     * @param array - array to sort.
     * @return = sorted array.
     */
    public int[] sort(int[] array) {
        boolean wasSwapped;
        do {
            wasSwapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    wasSwapped = true;
                }
            }
        } while (wasSwapped);
        return array;
    }
}
