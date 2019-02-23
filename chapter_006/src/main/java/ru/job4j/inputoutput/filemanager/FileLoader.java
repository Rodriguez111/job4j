package ru.job4j.inputoutput.filemanager;

import ru.job4j.inputoutput.filemanager.connection.Connection;

import java.io.File;

public class FileLoader implements FileLoad {

    private final Connection connection;

    public FileLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void sendFile(File file) {
        connection.fileToStream(file, file.length());
    }

    @Override
    public void receiveFile(File file) {
        connection.streamToFile(file, file.length());
    }
}
