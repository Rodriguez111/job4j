package pseudo;

import org.junit.Test;

import ru.job4j.pseudo.Triangle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("     o     ")
                                .append("\n")
                                .append("    o o    ")
                                .append("\n")
                                .append("   o   o   ")
                                .append("\n")
                                .append("  o     o  ")
                                .append("\n")
                                .append(" o       o ")
                                .append("\n")
                                .append("ooooooooooo").toString()
                )
        );
    }
}
