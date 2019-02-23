package ru.job4j.inputoutput.filemanager.utils;

import ru.job4j.inputoutput.filemanager.exceptions.ExceptionsHandler;

import java.io.*;

public class ConsoleManager {
    private  ExceptionsHandler<String> exceptionsHandlerString = new ExceptionsHandler();
    private  ExceptionsHandler<Integer> exceptionsHandlerInteger = new ExceptionsHandler();
    private InputStream input = System.in;


    public ConsoleManager(InputStream inputStream) {
        this.input = inputStream;
    }

    public ConsoleManager() {
    }

    public void print(String string) {
        System.out.println(string);
    }

    public String consoleStringReader(String question) {
        print(question);
       return exceptionsHandlerString.handleExceptionSupplier(this::unhandledConsoleStringReader);
    }

    public Integer consoleNumberReader() {
        return exceptionsHandlerInteger.handleExceptionSupplier(this::unhandledConsoleNumberReader);
    }

    private String unhandledConsoleStringReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        return br.readLine();
    }

    private Integer unhandledConsoleNumberReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        Integer number;
        while (true) {
            try {
                number = Integer.parseInt(br.readLine());
                break;
            } catch (NumberFormatException e) {
                print("Entered value is not number, try again:");
            }
        }
        return number;
    }

}
