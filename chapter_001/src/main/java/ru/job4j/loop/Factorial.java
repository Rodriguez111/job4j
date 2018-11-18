package ru.job4j.loop;

/**
 * Calculating factorial.
 */

public class Factorial {

    /**
     *
     * @param n = the number of which factorial to calculate.
     * @return factorial of n.
     */

    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
            return result;
    }
}
