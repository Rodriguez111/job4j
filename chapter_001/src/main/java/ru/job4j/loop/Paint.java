package ru.job4j.loop;

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
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.lineSeparator();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= column) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(ln);
        }
    return  stringBuilder.toString();
    }

    /**
     * Painting left side of pyramid.
     * @param height - height of pyramid.
     * @return
     */
    public String leftTrl(int height) {
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.lineSeparator();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (column >= width - row - 1) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(ln);
        }
        return  stringBuilder.toString();
    }


    /**
     * Painting whole pyramid.
     * @param height - height of pyramid.
     * @return
     */

    public String pyramid(int height) {
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.lineSeparator();
        int width = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= height - column - 1 && column <= row + height - 1) {
                    stringBuilder.append("^");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(ln);
        }
        return stringBuilder.toString();
    }


}
