package ru.job4j.inputoutput;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CheckByteStreamTest {
    CheckByteStream checkByteStream = new CheckByteStream();

    @Test
    public void shouldReturnTrue() {
       byte[] array = {5, 1, 9, 8};
       boolean actual = checkByteStream.isNumber(new ByteArrayInputStream(array));
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnFalse() {
        byte[] array = {5, 1, 9, 127};
        boolean actual = checkByteStream.isNumber(new ByteArrayInputStream(array));
        boolean expected = false;
        assertThat(actual, is(expected));
    }

}