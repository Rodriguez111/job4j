package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {
    private final Cell position;

    public PawnWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        if (!isWhitePawnWay(source, dest)) {
            throw new ImpossibleMoveException();
        }
        int deltaY = dest.y - source.y;
        Cell[] steps = new Cell[deltaY];
        for (int i = 1; i <= steps.length; i++) {
            steps[i - 1] = Cell.findCellByXY(source.x, source.y + i);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }

    private boolean isWhitePawnWay(Cell source, Cell dest) {
        boolean isWhitePawnWay = false;
        if (source.y == 1 && source.y == dest.y - 2 && source.x == dest.x) {
            isWhitePawnWay = true;
        }
        if (source.y == dest.y - 1 && source.x == dest.x) {
            isWhitePawnWay = true;
        }
        return isWhitePawnWay;
    }
}
