package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.QueenBlack;

import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenBlackTest {
    @Test
    public void whenQueenWayIsFromF8ToB4() throws ImpossibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.F8);
        Cell[] actual = queenBlack.way(Cell.F8, Cell.B4);
        Cell[] expected = {Cell.E7, Cell.D6, Cell.C5, Cell.B4};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenQueenWayIsFromD3ToD7() throws ImpossibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.D3);
        Cell[] actual = queenBlack.way(Cell.D3, Cell.D7);
        Cell[] expected = {Cell.D4, Cell.D5, Cell.D6, Cell.D7};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenQueenWayIsFromD7ToD3() throws ImpossibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.D7);
        Cell[] actual = queenBlack.way(Cell.D7, Cell.D3);
        Cell[] expected = {Cell.D6, Cell.D5, Cell.D4, Cell.D3};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenQueenWayIsFromE8ToD7() throws ImpossibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.E8);
        Cell[] actual = queenBlack.way(Cell.E8, Cell.D7);
        Cell[] expected = {Cell.D7};
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenQueenWayIsFromA5ToB3() throws ImpossibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.A5);
        Cell[] actual = queenBlack.way(Cell.A5, Cell.B3);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }


}
