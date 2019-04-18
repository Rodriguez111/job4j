package ru.job4j.siteparser.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static File propertiesFile = null;

    public static void setPropertiesFile(File propertiesFile) {
        PropertiesManager.propertiesFile = propertiesFile;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFile)) {

            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getSqlDriver() {
        return getProperties().getProperty("jdbc.driver");
    }

    public static String getDbUrl() {
        return getProperties().getProperty("jdbc.url");
    }

    public static String getDbDirPath() {
        File file = new File(getProperties().getProperty("jdbc.dbpath"));
        return file.getParent();
    }

    public static String getTime() {
        return getProperties().getProperty("cron.time");
    }
}
