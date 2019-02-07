package ru.job4j.streamapi;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StreamApiTest {

    @Test

    public void shouldReturn220() {
        StreamApi streamApi = new StreamApi();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int actual = streamApi.filter(array);
        int expected = 220;
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnMinus1IfNoResult() {
        StreamApi streamApi = new StreamApi();
        int[] array = {1, 3};
        int actual = streamApi.filter(array);
        int expected = -1;
        assertThat(actual, is(expected));
    }

    @Test
    public void shouldReturnMinus1IfArrayIsEmpty() {
        StreamApi streamApi = new StreamApi();
        int[] array = {};
        int actual = streamApi.filter(array);
        int expected = -1;
        assertThat(actual, is(expected));
    }




}