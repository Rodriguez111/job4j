package ru.job4j.pseudo;

import org.junit.Test;

import ru.job4j.pseudo.Triangle;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        String lineSeparator = System.lineSeparator();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("     o     ")
                                .append(lineSeparator)
                                .append("    o o    ")
                                .append(lineSeparator)
                                .append("   o   o   ")
                                .append(lineSeparator)
                                .append("  o     o  ")
                                .append(lineSeparator)
                                .append(" o       o ")
                                .append(lineSeparator)
                                .append("ooooooooooo").toString()
                )
        );
    }
}
