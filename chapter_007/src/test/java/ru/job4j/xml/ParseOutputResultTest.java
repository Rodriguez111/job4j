package ru.job4j.xml;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParseOutputResultTest {
    private final ConvertXSQT convertXSQT = new ConvertXSQT();
    private final Config config = new Config();
    private final File source = new File(config.getSource());
    private final File output = new File(config.getOutput());
    private final File transform = new File(config.getTransformPatternXsl());


    @Test
    void parse() {
        Entry entry1 = new Entry(1);
        Entry entry2 = new Entry(2);
        Entry entry3 = new Entry(3);
        Entry entry4 = new Entry(4);
        List<Entry> list = List.of(entry1, entry2, entry3, entry4);
        Element element = new Element(list);

        StoreXML storeXML = new StoreXML(source);
        storeXML.save(element);
        convertXSQT.convert(source, output, transform);

        ParseOutputResult parseOutputResult = new ParseOutputResult();
        parseOutputResult.parse();
        long actual = parseOutputResult.getHandler().getSummary();
        long expected = 10;
        assertThat(actual, is(expected));
    }
}