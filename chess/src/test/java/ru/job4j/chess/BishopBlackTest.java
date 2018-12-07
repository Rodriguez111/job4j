package ru.job4j.chess;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class BishopBlackTest {
    @Test
    public void whenBishopWayIsFromC8ToE6() {
       BishopBlack bishopBlack = new BishopBlack(Cell.C8);
       Cell[] actual = bishopBlack.way(Cell.C8, Cell.E6);
       Cell[] expected ={Cell.D7, Cell.E6};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenBishopWayIsFromF8ToA3() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        Cell[] actual = bishopBlack.way(Cell.F8, Cell.A3);
        Cell[] expected ={Cell.E7, Cell.D6, Cell.C5, Cell.B4, Cell.A3};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenBishopWayIsFromB2ToC3() {
        BishopBlack bishopBlack = new BishopBlack(Cell.B2);
        Cell[] actual = bishopBlack.way(Cell.B2, Cell.C3);
        Cell[] expected ={Cell.C3};
        assertThat(actual, is(expected));
    }

    @Test
    public void whenBishopWayIsFromF4ToE2ThenEmpty() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F4);
        Cell[] actual = bishopBlack.way(Cell.F4, Cell.E2);
        Cell[] expected ={};
        assertThat(actual, is(expected));
    }


}
