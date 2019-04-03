package ru.job4j.xml;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {
    private static final Logger LOG = LoggerFactory.getLogger(ConvertXSQT.class);
    private final Config config = new Config();

    private final TransformerFactory factory = TransformerFactory.newInstance();

    public void convert(File source, File dest, File scheme) {
        LOG.info("Converting primary XML file to another format...");
        BufferedInputStream inputStreamXSL = getFileInputStreamFromFile(scheme);
        BufferedInputStream inputStreamXML = getFileInputStreamFromFile(source);
        try {
            Transformer transformer = factory.newTransformer(
                    new StreamSource(inputStreamXSL)
            );

            transformer.transform(new StreamSource(
                            inputStreamXML),
                    new StreamResult(dest));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    private BufferedInputStream getFileInputStreamFromFile(File source) {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bis;
    }

}
