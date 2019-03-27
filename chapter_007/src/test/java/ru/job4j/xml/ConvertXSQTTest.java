package ru.job4j.xml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConvertXSQTTest {
    private final ConvertXSQT convertXSQT = new ConvertXSQT();
    private final Config config = new Config();
    private final File source = new File(config.getSource());
    private final File output = new File(config.getOutput());
    private final File transform = new File(config.getTransformPatternXsl());

    @Test
   public void shouldConvertToOtherXMLFormatWhenConvert() throws IOException {
        Entry entry1 = new Entry(1);
        Entry entry2 = new Entry(2);
        List<Entry> list = List.of(entry1, entry2);
        Element element = new Element(list);

        StoreXML storeXML = new StoreXML(source);
        storeXML.save(element);
       convertXSQT.convert(source, output, transform);

        BufferedReader reader = new BufferedReader(new FileReader(output));
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        reader.close();

        String actual = stringBuilder.toString();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry href=\"1\"/><entry href=\"2\"/></entries>";
        assertThat(actual, is(expected));
    }
}