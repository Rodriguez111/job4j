package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.KnightBlack;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class KnightBlackTest {
    @Test
    public void whenKnightWayIsFromF8ToD7() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.F8);
        Cell[] actual = knightBlack.way(Cell.F8, Cell.D7);
        Cell[] expected = {Cell.D7};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenKnightWayIsFromD5ToB4() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.D5);
        Cell[] actual = knightBlack.way(Cell.D5, Cell.B4);
        Cell[] expected = {Cell.B4};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenKnightWayIsFromD5ToC3() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.D5);
        Cell[] actual = knightBlack.way(Cell.D5, Cell.C3);
        Cell[] expected = {Cell.C3};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenKnightWayIsFromD5ToE3() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.D5);
        Cell[] actual = knightBlack.way(Cell.D5, Cell.E3);
        Cell[] expected = {Cell.E3};
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenKnightWayIsFromD5ToE4ThenException() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.D5);
        Cell[] actual = knightBlack.way(Cell.D5, Cell.E4);
        Cell[] expected = {};
        assertThat(actual, is(expected));
    }
}
