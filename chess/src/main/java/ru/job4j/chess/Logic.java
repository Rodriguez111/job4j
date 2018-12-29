package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.KnightBlack;
import ru.job4j.chess.firuges.exceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.white.KnightWhite;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        boolean rst = false;
        int index = findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException();
        }
            Cell[] steps = this.figures[index].way(source, dest);
        if (steps.length > 0) {
            for (Cell eachCell : steps) {
                if (findBy(eachCell) != -1) {
                    throw new OccupiedWayException();
                }
            }
        }
            if (steps.length > 0) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        final int[] result = new int[2];
        result[0] = 0;
        result[1] = -1;
        Arrays.stream(figures).forEach(figure -> {
            if (figure != null && figure.position().equals(cell)) {
                result[1] = result[0];
            }
            result[0]++;
        });
        return result[1];
    }

}
