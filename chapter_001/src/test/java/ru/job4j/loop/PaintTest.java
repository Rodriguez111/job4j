package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String result = paint.rightTrl(4);
        StringJoiner sj = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        assertThat(result, is(sj.add("^   ").add("^^  ").add("^^^ ").add("^^^^").toString()));
        System.out.println(paint.rightTrl(4));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String result = paint.leftTrl(4);
        StringJoiner sj = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        assertThat(result, is(sj.add("   ^").add("  ^^").add(" ^^^").add("^^^^").toString()));
        System.out.println(paint.leftTrl(4));
    }

    @Test
    public void whenPyramid4Height() {
        Paint paint = new Paint();
        String result = paint.pyramid(4);
        StringJoiner sj = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        assertThat(result, is(sj.add("   ^   ").add("  ^^^  ").add(" ^^^^^ ").add("^^^^^^^").toString()));
        System.out.println(paint.pyramid(4));
    }
}
