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
public class QueenBlack extends Figure {
    private final Cell position;

    public QueenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isQueenWay(source, dest)) {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[0];
        if (isDiagonal(source, dest)) {
            steps = diagonalsMove(source, dest);
        }
        if (isRookWay(source, dest)) {
            steps = verticalAndHorizontalMove(source, dest);
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }

    private boolean isQueenWay(Cell source, Cell dest) {
        boolean isQueenWay = false;
        if (isDiagonal(source, dest) || isRookWay(source, dest)) {
            isQueenWay = true;
        }
        return isQueenWay;
    }
}
