package ru.job4j.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    public Config() {
        init();
    }

    public void init() {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("xml/app.properties")) {
            values.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

}
