package ru.job4j.inputoutput.chat.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class OutputForTest implements Output {
    private final ByteArrayOutputStream baos;
    private final String lineSeparator = System.lineSeparator();

    public OutputForTest(ByteArrayOutputStream baos) {
        this.baos = baos;
    }

    @Override
    public void writeLog(String line) throws IOException {
        baos.write((line + lineSeparator).getBytes());
    }
}
