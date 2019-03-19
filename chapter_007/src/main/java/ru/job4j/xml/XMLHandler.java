package ru.job4j.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

    private long summary;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String attr = attributes.getValue("href");
        if (attr != null && !attr.isEmpty()) {
            summary += Long.valueOf(attr);
        }
    }

    public long getSummary() {
        return summary;
    }
}
