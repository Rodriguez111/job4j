package generic.set;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    SimpleSet<String> simpleSet = new SimpleSet<>();
    PrintStream original;
    PrintStream newStream;
    String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setStream() {
        original = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @AfterEach
    public void returnStream() {

        System.setOut(original);
    }

    @Test
    public void shouldAddOnlyUniqObjects() {
        simpleSet.add("one");
        simpleSet.add("two");
        simpleSet.add("three");
        simpleSet.add("one");

        StringBuilder sb = new StringBuilder();
        sb.append("one")
                .append(lineSeparator)
                .append("two")
                .append(lineSeparator)
                .append("three")
                .append(lineSeparator);

        for (String e : simpleSet) {
            System.out.println(e);
        }
        String expected = sb.toString();
        String actual = baos.toString();
        assertThat(actual, is(expected));
    }

}