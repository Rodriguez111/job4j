package ru.job4j.xml;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StoreXMLTest {
    private final static String PATH = System.getProperty("java.io.tmpdir");
    private final static String XML_FILE_PATH = PATH + "/JavaTest/xmlTestFile.xml";
    private final static String LS = System.lineSeparator();

    @BeforeEach
    public void createDir() {
      File dir =  new File(PATH + "/JavaTest");
        dir.mkdirs();
    }

    @Test
   public void shouldSaveElementToXMLWhenSaveElement() throws IOException {
        Entry entry1 = new Entry(1);
        Entry entry2 = new Entry(2);
        List<Entry> list = List.of(entry1, entry2);
        Element element = new Element(list);

        StoreXML storeXML = new StoreXML(new File(XML_FILE_PATH));
        storeXML.save(element);

        BufferedReader reader = new BufferedReader(new FileReader(XML_FILE_PATH));
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = reader.readLine();
        }
        reader.close();

        String actual = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<element>")
                .append("    <entryList>")
                .append("        <value>1</value>")
                .append("    </entryList>")
                .append("    <entryList>")
                .append("        <value>2</value>")
                .append("    </entryList>")
                .append("</element>");

        String expected = stringBuilder.toString();
        assertThat(actual, is(expected));
    }
}