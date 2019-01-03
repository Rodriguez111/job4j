package ru.job4j.tactactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i < table.length; i++) {
           int x = startX + deltaX * i;
            int y  = startY + deltaY * i;
            Figure3T current = table[y][x];
            result = predicate.test(current);
            if (!result) {
                break;
            }

        }
        return result;
    }


    private boolean isWinner(Predicate<Figure3T> predicate) {
        return this.fillBy(predicate, table.length - 1, 0, -1, 1)
                || this.fillBy(predicate, 0, 0, 1, 1)
                || this.fillBy(predicate, 0, 0, 0, 1)
                || this.fillBy(predicate, 1, 0, 0, 1)
                || this.fillBy(predicate, 2, 0, 0, 1)
                || this.fillBy(predicate, 0, 0, 1, 0)
                || this.fillBy(predicate, 0, 1, 1, 0)
                || this.fillBy(predicate, 0, 2, 1, 0);
    }

    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }


    public boolean isEngaged(Figure3T figure) {
        return figure.hasMarkX() || figure.hasMarkO();
    }



    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j].hasMarkX() && !table[i][j].hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
