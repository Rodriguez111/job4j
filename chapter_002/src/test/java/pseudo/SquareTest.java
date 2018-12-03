package pseudo;

import org.junit.Test;
import ru.job4j.pseudo.Square;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {

       @Test
        public void whenDrawSquare() {
            Square square = new Square();
            assertThat(
                    square.draw(),
                    is(
                        new StringBuilder()
                           .append("oooooooo")
                           .append("\n")
                           .append("0      0")
                           .append("\n")
                           .append("0      0")
                           .append("\n")
                           .append("oooooooo").toString()
                    )
            );
        }

}
