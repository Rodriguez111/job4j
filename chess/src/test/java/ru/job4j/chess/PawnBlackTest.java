package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnBlackTest {

    @Test
    public void whenPawnWayIsFromB7ToB5() {
        PawnBlack pawnBlack = new PawnBlack(Cell.B7);
        Cell[] actual = pawnBlack.way(Cell.B7, Cell.B5);
        Cell[] expected ={Cell.B6, Cell.B5};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromD7ToD6() {
        PawnBlack pawnBlack = new PawnBlack(Cell.D7);
        Cell[] actual = pawnBlack.way(Cell.D7, Cell.D6);
        Cell[] expected ={Cell.D6};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromD6ToD4ThenEmpty() {
        PawnBlack pawnBlack = new PawnBlack(Cell.D6);
        Cell[] actual = pawnBlack.way(Cell.D6, Cell.D4);
        Cell[] expected ={};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromD4ToD5ThenEmpty() {
        PawnBlack pawnBlack = new PawnBlack(Cell.D4);
        Cell[] actual = pawnBlack.way(Cell.D4, Cell.D5);
        Cell[] expected ={};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenPawnWayIsFromD7ToC6ThenEmpty() {
        PawnBlack pawnBlack = new PawnBlack(Cell.D7);
        Cell[] actual = pawnBlack.way(Cell.D7, Cell.C6);
        Cell[] expected ={};
        assertThat(actual, is(expected));
    }

}
