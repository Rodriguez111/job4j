package ru.job4j.loop;

/**
 * This class counts something.
 */

public class Counter {


    /**
     *
      * @param start - start number.
     * @param finish - finish number.
     * @return - sum of even numbers
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result = result + i;
            }
        }
        return result;
    }
}
