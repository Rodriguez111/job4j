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
    private final String source = ConvertXSQT.class.getClassLoader().getResource(config.get("fileXMLSource")).getFile();
    private final String output = ConvertXSQT.class.getClassLoader().getResource(config.get("fileXMLOutput")).getFile();
    private final String transformPatternXsl = ConvertXSQT.class.getClassLoader().getResource(config.get("fileXSL")).getFile();

    private final TransformerFactory factory = TransformerFactory.newInstance();



    public void convert(File source, File dest, File scheme) {
        LOG.info("Converting primary XML file to another format...");
        ByteArrayInputStream byteArrayInputStreamXSL = getByteArrayInputStreamFromFile(scheme);
        ByteArrayInputStream byteArrayInputStreamXML = getByteArrayInputStreamFromFile(source);
        try {
            Transformer transformer = factory.newTransformer(
                    new StreamSource(byteArrayInputStreamXSL)
            );

            transformer.transform(new StreamSource(
                            byteArrayInputStreamXML),
                    new StreamResult(dest));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private ByteArrayInputStream getByteArrayInputStreamFromFile(File source) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
    return bais;
    }

    public String getSource() {
        return source;
    }

    public String getOutput() {
        return output;
    }

    public String getTransformPatternXsl() {
        return transformPatternXsl;
    }

}
