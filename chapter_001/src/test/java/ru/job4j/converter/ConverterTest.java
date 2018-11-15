package ru.job4j.converter;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    @Test
    public void when50DollarToRubleThen3000() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(50);
        assertThat(result, is(3000));
    }

    @Test
    public void when80EuroToRubleThen5600() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(80);
        assertThat(result, is(5600));
    }
}
