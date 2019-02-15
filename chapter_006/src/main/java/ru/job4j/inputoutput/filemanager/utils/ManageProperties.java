package ru.job4j.inputoutput.filemanager.utils;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ManageProperties {

    private URL rootPath = getClass().getClassLoader().getResource("");
    private final String propertiesPath = rootPath.getFile() + "/ru/job4j/inputoutput/filemanager/app.properties";
    private final int port;
    private final String serverIpAddress;


    public ManageProperties() {
        File propertiesFile = new File(propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = Integer.parseInt(properties.getProperty("port"));
        this.serverIpAddress = properties.getProperty("ip");
    }

    public int getPort() {
        return port;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }
}
