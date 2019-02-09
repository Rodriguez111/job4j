package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DropAbusesTest {

    private DropAbuses dropAbuses = new DropAbuses();
    private String originalText = "Сама идея создания, языка принадлежала? молодому разработчику, компании Sun Microsystems Патрику Нотану.";
    private InputStream is = new ByteArrayInputStream(originalText.getBytes());
    private OutputStream os = new ByteArrayOutputStream();
    private String[] abusedWords = {"создания", "принадлежала", "компании", "Microsystems"};

    @Test
    public void shouldReturnStreamWithoutAbusedWords() {
        dropAbuses.dropAbuses(is, os, abusedWords);
        String actual = os.toString();
        String expected = "Сама идея , языка ? молодому разработчику,  Sun  Патрику Нотану.";
        assertThat(actual, is(expected));
    }

}