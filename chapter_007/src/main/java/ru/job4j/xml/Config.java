package ru.job4j.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    private final String source;
    private final String output;
    private final String transformPatternXsl;

    public Config() {
        init();
        this.source = Config.class.getClassLoader().getResource(values.getProperty("fileXMLSource")).getFile();
        this.output = Config.class.getClassLoader().getResource(values.getProperty("fileXMLOutput")).getFile();
        this.transformPatternXsl = Config.class.getClassLoader().getResource(values.getProperty("fileXSL")).getFile();
    }

    public void init() {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("xml/app.properties")) {
            values.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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

        public String get(String key) {
        return this.values.getProperty(key);
    }

}
