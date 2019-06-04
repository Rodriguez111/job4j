package ru.job4j.cash.ver1.resulthandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads and return text body of the text file.
 */

public class TextReader implements SearchResultHandler<File, String> {

    /**
     *
     * @param searchResult - file  to read.
     * @return text body of the file.
     */

    @Override
    public String handleResult(File searchResult) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(searchResult))) {
            String readString = bufferedReader.readLine();
            while (readString != null) {
                sb.append(readString);
                readString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
