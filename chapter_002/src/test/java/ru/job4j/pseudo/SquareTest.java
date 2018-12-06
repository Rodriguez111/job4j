package ru.job4j.pseudo;

import org.junit.Test;
import ru.job4j.pseudo.Square;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {

       @Test
        public void whenDrawSquare() {
            Square square = new Square();
           String lineSeparator = System.lineSeparator();
            assertThat(
                    square.draw(),
                    is(
                        new StringBuilder()
                           .append("oooooooo")
                           .append(lineSeparator)
                           .append("0      0")
                           .append(lineSeparator)
                           .append("0      0")
                           .append(lineSeparator)
                           .append("oooooooo").toString()
                    )
            );
        }

}
