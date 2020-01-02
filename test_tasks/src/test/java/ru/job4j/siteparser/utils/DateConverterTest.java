package ru.job4j.siteparser.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DateConverterTest {


    @Test
    public void whenYesterdayThenReturnYesterdayDate() {
        String html = "<td style=\"text-align:center\" class=\"altCol\">вчера, 11:46</td>";
        Document document = Jsoup.parse(html, "", Parser.xmlParser());
        Element element = document;
        String actual = DateConverter.getDateFromElement(element);
        String expected = DateConverter.getYesterdayOrTodayDate(element.text(), 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTodayThenReturnTodayDate() {
        String html = "<td style=\"text-align:center\" class=\"altCol\">сегодня, 11:46</td>";
        Document document = Jsoup.parse(html, "", Parser.xmlParser());
        Element element = document;
        String actual = DateConverter.getDateFromElement(element);
        String expected = DateConverter.getYesterdayOrTodayDate(element.text(), 0);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenElementIsInCorrectDateThenTrue() {
        String html = "<td style=\"text-align:center\" class=\"altCol\">сегодня, 11:46</td>";
        Document document = Jsoup.parse(html, "", Parser.xmlParser());
        Element element = document;

        boolean actual = DateConverter.checkElementIsInDate(element);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenElementIsInCorrectDateThenTrue2() {
        String html = "<td style=\"text-align:center\" class=\"altCol\">29 мар 20, 13:16</td>";
        Document document = Jsoup.parse(html, "", Parser.xmlParser());
        Element element = document;

        boolean actual = DateConverter.checkElementIsInDate(element);
        boolean expected = true;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenElementIsNotInCorrectDateThenFalse() {
        String html = "<td style=\"text-align:center\" class=\"altCol\">29 мар 18, 13:16</td>";
        Document document = Jsoup.parse(html, "", Parser.xmlParser());
        Element element = document;

        boolean actual = DateConverter.checkElementIsInDate(element);
        boolean expected = false;
        assertThat(actual, is(expected));
    }

    @Test
    public void whenInvokeWithTextDateThenReturnDigitalDate() {
        String date = "29 мар 19, 13:16";

        DateConverter dateConverter = new DateConverter();
        String actual = dateConverter.getDate(date);

        String expected = "2019-03-29 13:16";
        assertThat(actual, is(expected));
    }

}