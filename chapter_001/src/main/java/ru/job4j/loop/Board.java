package ru.job4j.loop;

/**
 * Painting chess board
 */

public class Board {

    /**
     *
     * @param width - number of cells wide.
     * @param height - number of cells height.
     * @return
     */
    public String paint(int width, int height) {
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.lineSeparator();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (j % 2 == 0) {
                    if (i % 2 == 0) {
                        stringBuilder.append("X");
                    } else {
                        stringBuilder.append(" ");
                    }
                } else {
                    if (i % 2 == 0) {
                        stringBuilder.append(" ");
                    } else {
                        stringBuilder.append("X");
                    }
                }
            }
            stringBuilder.append(ln);
        }
        return stringBuilder.toString();
    }
}
