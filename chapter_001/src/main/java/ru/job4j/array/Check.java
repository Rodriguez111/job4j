package ru.job4j.array;

/**
 * Checking if array contains different values
 */

public class Check {

    /**
     *
     * @param data - array to check.
     * @return - check result
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean firstElement = data[0];
        for (boolean eachElement : data) {
            if (eachElement != firstElement) {
                result = false;
                break;
            }
        }
        return result;
    }
}
