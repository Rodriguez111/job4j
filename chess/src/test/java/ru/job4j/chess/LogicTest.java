package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;
import ru.job4j.chess.firuges.exceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.exceptions.OccupiedWayException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class LogicTest {

    @Test (expected = OccupiedWayException.class)
    public void whenMoveFromA8ToC6ThenOccupiedWayException() throws OccupiedWayException, FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new BishopBlack(Cell.A8));
        boolean actual = logic.move(Cell.A8, Cell.C6);
        Exception expected = new OccupiedWayException();
        assertThat(actual, is(expected));
    }

    @Test (expected = FigureNotFoundException.class)
    public void whenMoveFromA7ToC6ThenFigureNotFoundException() throws OccupiedWayException, FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new BishopBlack(Cell.A8));
        boolean actual = logic.move(Cell.A7, Cell.C6);
        Exception expected = new FigureNotFoundException();
        assertThat(actual, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenMoveBishopFromA8ToB8ThenImpossibleMoveException() throws OccupiedWayException, FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new BishopBlack(Cell.A8));
        boolean actual = logic.move(Cell.A8, Cell.B8);
        Exception expected = new ImpossibleMoveException();
        assertThat(actual, is(expected));
    }


}
