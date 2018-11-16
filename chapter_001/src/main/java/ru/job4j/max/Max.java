package ru.job4j.max;

/**
 * Maximum value determination.
 */

public class Max {

    /**
     *
     * @param first - first value ;
     * @param second - second value;
     * @return - maximum of two values
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     *
     * @param first - first value ;
     * @param second - second value;
     * @param third - third value;
     * @return
     */
    public int max(int first, int second, int third) {
        int temp = max(first, second);
        return max(temp, third);
    }

}
