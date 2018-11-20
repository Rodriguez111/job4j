package ru.job4j.array;

/**
 * Finding something in the array.
 */

public class FindLoop {

    /**
     *
     * @param data - array to search in.
     * @param el - index of element to search.
     * @return - found index.
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int index = 0; index != data.length; index++) {
            if (data[index] == el) {
                result = index;
                break;
            }
        }
        return result;
    }
}
