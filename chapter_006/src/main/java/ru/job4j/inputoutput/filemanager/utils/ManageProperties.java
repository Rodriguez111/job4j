package ru.job4j.inputoutput.filemanager.utils;

import java.io.IOException;
import java.util.Properties;

public class ManageProperties {
    private final int port;
    private final String serverIpAddress;

    public ManageProperties() {

        Properties properties = new Properties();
        try {
            properties.load(ManageProperties.class.getClassLoader().getResourceAsStream("fileManager.properties"));
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
