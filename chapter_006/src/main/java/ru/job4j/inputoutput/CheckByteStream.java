package ru.job4j.inputoutput;

import java.io.IOException;
import java.io.InputStream;

public class CheckByteStream {

    boolean isNumber(InputStream in) {
        boolean isNumber = false;
        try {
            int result = in.read();
            while (in.available() > 0) {
                if (in.read() % 2 == 0) {
                    isNumber = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return isNumber;
    }
}
