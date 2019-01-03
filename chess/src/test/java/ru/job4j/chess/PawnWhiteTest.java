package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.PawnWhite;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnWhiteTest {

    @Test
    public void whenPawnWayIsFromC2ToC4() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.C2);
        Cell[] actual = pawnWhite.way(Cell.C2, Cell.C4);
        Cell[] expected = {Cell.C3, Cell.C4};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromC2ToC3() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.C2);
        Cell[] actual = pawnWhite.way(Cell.C2, Cell.C3);
        Cell[] expected = {Cell.C3};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromC6ToC7() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.C6);
        Cell[] actual = pawnWhite.way(Cell.C6, Cell.C7);
        Cell[] expected = {Cell.C7};
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenPawnWayIsFromC6ToC8() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.C6);
        Cell[] actual = pawnWhite.way(Cell.C6, Cell.C8);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenPawnWayIsFromC2ToC1() throws ImpossibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.C2);
        Cell[] actual = pawnWhite.way(Cell.C2, Cell.C1);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }




}
