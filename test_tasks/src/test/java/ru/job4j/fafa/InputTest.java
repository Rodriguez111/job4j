package ru.job4j.fafa;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class InputTest {

    private Input input;
    private ByteArrayInputStream bais;

    @Test
    public void whenInput10ThenReturn10() {
        bais = new ByteArrayInputStream("10".getBytes());
        input = new Input(bais);

        int actual = input.getValue();
        int expected = 10;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInputTenThenAskInputAgainAndThrowExceptionBecauseThereIsNoMoreDataInStream() {
        bais = new ByteArrayInputStream("ten".getBytes());
        input = new Input(bais);
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            input.getValue();
        });
        assertEquals(null, exception.getMessage());
    }

}