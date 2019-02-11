package ru.job4j.inputoutput.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements Input {

    @Override
    public String typeMassage() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = "";
        try {
            message = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
