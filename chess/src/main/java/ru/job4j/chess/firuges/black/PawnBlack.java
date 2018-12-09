package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack extends Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isBlackPawnWay(source, dest)) {
            throw new ImpossibleMoveException();
        }
        int deltaY = source.y - dest.y;
        Cell[] steps = new Cell[deltaY];
            for (int i = 1; i <= steps.length; i++) {
                  steps[i - 1] = Cell.findCellByXY(source.x, source.y - i);
                }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }

    private boolean isBlackPawnWay(Cell source, Cell dest) {
        boolean isBlackPawnWay = false;
        if (source.y == 6 && source.y == dest.y + 2 && source.x == dest.x) {
            isBlackPawnWay = true;
        }
        if (source.y == dest.y + 1 && source.x == dest.x) {
            isBlackPawnWay = true;
        }
        return isBlackPawnWay;
    }



}
