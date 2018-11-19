package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Painting pyramid.
 */

public class Paint {


    /**
     * Painting right side of pyramid.
     * @param height - height of pyramid.
     * @return
     */

    public String rightTrl(int height) {
    return loopBy(height, height, (row, column) -> row >= column);
    }

    /**
     * Painting left side of pyramid.
     * @param height - height of pyramid.
     * @return
     */
    public String leftTrl(int height) {
        return loopBy(height, height, (row, column) -> column >= height - row - 1);
    }


    /**
     * Painting whole pyramid.
     * @param height - height of pyramid.
     * @return
     */

    public String pyramid(int height) {
        return loopBy(height, 2 * height - 1, (row, column) -> row >= height - column - 1 && column <= row + height - 1);
    }


    /**
     *
     * @param height - height of pyramid.
     * @param width - width of pyramid.
     * @param predict - check condition.
     * @return
     */
    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }


}
