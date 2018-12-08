package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.RookBlack;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class RookBlackTest {
    @Test
    public void whenRookWayIsFromB7ToB2() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.B7);
        Cell[] actual = rookBlack.way(Cell.B7, Cell.B2);
        Cell[] expected ={Cell.B6, Cell.B5, Cell.B4, Cell.B3, Cell.B2};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenRookWayIsFromB2ToB7() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.B2);
        Cell[] actual = rookBlack.way(Cell.B2, Cell.B7);
        Cell[] expected ={Cell.B3, Cell.B4, Cell.B5, Cell.B6, Cell.B7};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenRookWayIsFromA4ToH4() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A4);
        Cell[] actual = rookBlack.way(Cell.A4, Cell.H4);
        Cell[] expected ={Cell.B4, Cell.C4, Cell.D4, Cell.E4, Cell.F4, Cell.G4, Cell.H4};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenRookWayIsFromH4ToA4() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.H4);
        Cell[] actual = rookBlack.way(Cell.H4, Cell.A4);
        Cell[] expected ={Cell.G4, Cell.F4, Cell.E4, Cell.D4, Cell.C4, Cell.B4, Cell.A4};
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenRookWayIsFromB7ToC5ThenException() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.B7);
        Cell[] actual = rookBlack.way(Cell.B7, Cell.C5);
        Cell[] expected ={};
        assertThat(actual, is(expected));
    }
}
