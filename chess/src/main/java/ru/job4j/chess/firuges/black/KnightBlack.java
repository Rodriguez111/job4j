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
public class KnightBlack extends Figure {
    private final Cell position;

    public KnightBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isKnightWay(source, dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[]{Cell.findCellByXY(dest.x, dest.y)};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }

    private boolean isKnightWay(Cell source, Cell dest) {
        boolean isKnightWay = false;
        if (
                (dest.x == source.x + 1 && dest.y == source.y + 2)
                        || (dest.x == source.x + 1 && dest.y == source.y - 2)
                        || (dest.x == source.x - 1 && dest.y == source.y + 2)
                        || (dest.x == source.x - 1 && dest.y == source.y - 2)

                        || (dest.x == source.x + 2 && dest.y == source.y + 1)
                        || (dest.x == source.x + 2 && dest.y == source.y - 1)
                        || (dest.x == source.x - 2 && dest.y == source.y + 1)
                        || (dest.x == source.x - 2 && dest.y == source.y - 1)
        ) {
            isKnightWay = true;
        }
        return isKnightWay;
    }

}
