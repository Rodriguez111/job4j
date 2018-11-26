package ru.job4j.array;

/**
 * Sorting array.
 */

public class BubbleSort {

    /**
     * Sorting array ascending.
     * @param array - array to sort.
     * @return = sorted array.
     * int count - после каждого перебора самый большой элемент оказывается наверху, поэтому каждый разз уменьшаеи кол-во переборов на 1
     */
    public int[] sort(int[] array) {
        boolean wasSwapped;
        int count = array.length;
        do {
                wasSwapped = false;
                for (int i = 0; i < count - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        wasSwapped = true;
                    }
                }
            count--;

        } while (wasSwapped);
        return array;
    }
}
