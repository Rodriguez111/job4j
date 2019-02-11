package ru.job4j.inputoutput.chat;

import java.io.ByteArrayInputStream;
import java.util.List;

public class InputForTest implements Input {

  private final List<String> listOfMessages;


    public InputForTest(List<String> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
    private int count = 0;
    @Override
    public String typeMassage() {
        String message = "";

       if (count < listOfMessages.size()) {
            message = listOfMessages.get(count++);
        }
        return message;
    }
}
