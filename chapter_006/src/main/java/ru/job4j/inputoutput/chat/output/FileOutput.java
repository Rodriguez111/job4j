package ru.job4j.inputoutput.chat.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutput implements Output {
    private final String logFile = getClass().getClassLoader().getResource("ru/job4j/inputoutput/chat/textlog.txt").getFile();

    @Override
    public void writeLog(String line) throws IOException {
        OutputStream os = new FileOutputStream(logFile);
        byte[] buffer = line.getBytes();
        os.write(buffer, 0, buffer.length);
    }
}
