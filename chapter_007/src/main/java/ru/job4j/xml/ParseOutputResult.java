package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParseOutputResult {
    private static final Logger LOG = LoggerFactory.getLogger(ParseOutputResult.class);
    private final Config config = new Config();
    private final String output = config.getOutput();
    private final static XMLHandler XML_HANDLER = new XMLHandler();

    public void parse() {
        LOG.info("Parsing destination XML and calculating result...");
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(new File(output), XML_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void outputResult() {
        System.out.println("Result sum = " + XML_HANDLER.getSummary());
    }

    public XMLHandler getHandler() {
        return XML_HANDLER;
    }
}
