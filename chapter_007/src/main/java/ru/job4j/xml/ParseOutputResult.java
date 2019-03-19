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
    private final String output = StoreXML.class.getClassLoader().getResource(config.get("fileXMLOutput")).getFile();
    private SAXParserFactory parserFactory;
    private SAXParser parser;
    private XMLHandler handler = new XMLHandler();

    public ParseOutputResult() {
        initParser();
    }

    private void initParser() {
        this.parserFactory = SAXParserFactory.newInstance();
        try {
            this.parser = parserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void parse() {
        LOG.info("Parsing destination XML and calculating result...");
        try {
            parser.parse(new File(output), handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputResult() {
        System.out.println("Result sum = " + handler.getSummary());
    }

    public XMLHandler getHandler() {
        return handler;
    }
}
