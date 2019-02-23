package ru.job4j.inputoutput.filemanager.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ConsoleEmulatorManager implements InputOutputManager {

    private final List<String> listOfMessages;
    private ByteArrayInputStream byteArrayInputStream;
    private int count = 0;


    public ConsoleEmulatorManager(List<String> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }


    @Override
    public void print(String string) {
        byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
    }

    @Override
    public String consoleStringReader(String question) {
        print(question);
        String result = "";
        if(count < listOfMessages.size()) {
            result = listOfMessages.get(count++);
        }
        return result;
    }
}
