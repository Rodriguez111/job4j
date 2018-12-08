package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.KingBlack;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingBlackTest {
    @Test(expected = ImpossibleMoveException.class)
    public void whenKingWayIsFromF4ToF6ThenException() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.F4);
        Cell[] actual = kingBlack.way(Cell.F4, Cell.F6);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenKingWayIsFromF4ToH2ThenException() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.F4);
        Cell[] actual = kingBlack.way(Cell.F4, Cell.H2);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenKingWayIsFromF4ToF5ThenF5() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.F4);
        Cell[] actual = kingBlack.way(Cell.F4, Cell.F5);
        Cell[] expected = {Cell.F5};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenKingWayIsFromC6ToB7ThenB7() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.C6);
        Cell[] actual = kingBlack.way(Cell.C6, Cell.B7);
        Cell[] expected = {Cell.B7};
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenKingWayIsFromC6ToB7ThenException() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.C6);
        Cell[] actual = kingBlack.way(Cell.C6, Cell.A8);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }

}
