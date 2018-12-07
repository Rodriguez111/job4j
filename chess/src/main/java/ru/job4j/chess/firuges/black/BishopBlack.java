package ru.job4j.chess.firuges.black;


import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if(Math.abs(deltaX) == Math.abs(deltaY)) {
            steps = new Cell[Math.abs(deltaX)];
            for (int i = 1; i <= steps.length; i++) {
                for (Cell eachCell : Cell.values()) {
                    int xx = i;
                    int yy = i;
                    if(deltaX < 0) {xx = -i;}
                    if(deltaY < 0) {yy = -i;}

                    if (eachCell.x == source.x + xx && eachCell.y == source.y + yy){
                        steps[i-1] = eachCell;
                        break;
                    }
                }
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
