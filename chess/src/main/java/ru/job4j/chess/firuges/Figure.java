package ru.job4j.chess.firuges;

import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

public abstract class Figure {
    public abstract Cell position();

    public  abstract  Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public  String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    public  abstract  Figure copy(Cell dest);

    public boolean isDiagonal (Cell source, Cell dest) {
        boolean isDiagonal = false;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if(Math.abs(deltaX) == Math.abs(deltaY)) {
            isDiagonal = true;
        }
        return isDiagonal;
    }

    public boolean isRookWay (Cell source, Cell dest) {
        boolean isRookWay = false;
        if(dest.x == source.x) {
            isRookWay = true;
        }
        if(dest.y == source.y) {
            isRookWay = true;
        }
        return isRookWay;
    }

    public Cell[] diagonalsMove(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;

        steps = new Cell[Math.abs(deltaX)];
        int newX = source.x;
        int newY = source.y;
        for (int i = 1; i <= steps.length; i++) {
            if(deltaX < 0) {
                newX = source.x - i;
            } else {
                newX = source.x + i;
            }
            if(deltaY < 0) {
                newY = source.y - i;
            } else {
                newY = source.y + i;
            }
            steps[i - 1] = Cell.findCellByXY(newX, newY);
        }
        return steps;
    }

    public Cell[] verticalAndHorizontalMove(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if(dest.x == source.x) {
            int deltaY = dest.y - source.y;
            steps = new Cell[Math.abs(deltaY)];
            for(int i = 1; i <= steps.length; i++) {

                if(deltaY < 0) {
                    steps[i-1] = Cell.findCellByXY(source.x, source.y - i);
                } else {
                    steps[i-1] = Cell.findCellByXY(source.x, source.y + i);
                }

            }
        }
        if(dest.y == source.y) {
            int deltaX = dest.x - source.x;
            steps = new Cell[Math.abs(deltaX)];
            for(int i = 1; i <= steps.length; i++) {

                if(deltaX < 0) {
                    steps[i-1] = Cell.findCellByXY(source.x - i, source.y);
                } else {
                    steps[i-1] = Cell.findCellByXY(source.x + i, source.y);
                }
            }
        }
        return steps;

    }




}
