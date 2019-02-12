package ru.job4j.inputoutput.chat.output;

import java.io.IOException;

public interface Output {
    void writeLog(String line) throws IOException;
}
