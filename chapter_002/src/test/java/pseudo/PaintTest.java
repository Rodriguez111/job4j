package pseudo;

import org.junit.Test;
import ru.job4j.pseudo.Paint;
import ru.job4j.pseudo.Square;
import ru.job4j.pseudo.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        Paint paint = new Paint();
        paint.setShape(new Square());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        paint.executeShape();
        System.setOut(original);
        String actual = baos.toString();
        String expected = new StringBuilder()
                .append("oooooooo")
                .append("\n")
                .append("0      0")
                .append("\n")
                .append("0      0")
                .append("\n")
                .append("oooooooo")
                .append("\r\n").toString();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDrawTriangle() {
        Paint paint = new Paint();
        paint.setShape(new Triangle());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        paint.executeShape();
        System.setOut(original);
        String actual = baos.toString();
        String expected = new StringBuilder()
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
                .append("ooooooooooo")
                .append("\r\n").toString();
        assertThat(actual, is(expected));
    }

}
