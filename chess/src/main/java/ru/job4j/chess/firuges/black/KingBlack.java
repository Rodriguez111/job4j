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
public class KingBlack extends Figure {
    private final Cell position;

    public KingBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if(!isKingWay(source, dest)) {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[1];
        steps[0] =  Cell.findCellByXY(dest.x, dest.y);


        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }

    private boolean isKingWay(Cell source, Cell dest) {
        boolean isKingWay = false;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if ((dest.x == source.x && dest.y == source.y + 1)
            || (dest.x == source.x && dest.y == source.y - 1)
                || (dest.x == source.x + 1 && dest.y == source.y)
                || (dest.x == source.x - 1 && dest.y == source.y)
                || ((Math.abs(deltaX) == Math.abs(deltaY)) && Math.abs(deltaX) == 1)
        ) {
            isKingWay = true;
        }
        return isKingWay;
    }
}
