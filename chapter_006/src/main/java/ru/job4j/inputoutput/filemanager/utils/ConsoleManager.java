package ru.job4j.inputoutput.filemanager.utils;

import ru.job4j.inputoutput.filemanager.exceptions.SupplierTypeExceptionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleManager<T> {
    public static void print(String string) {
        System.out.println(string);
    }


    public String consoleStringReader() {
       return (String) handleException(this::unhandledConsoleStringReader);
    }

    public Integer consoleNumberReader() {
        return (Integer) handleException(this::unhandledConsoleNumberReader);
    }


    public  String unhandledConsoleStringReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    private  Integer unhandledConsoleNumberReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer number = 0;
        while (true) {
            try{
                number = Integer.parseInt(br.readLine());
                break;
            } catch (NumberFormatException e) {
                print("Entered value is not number, try again:");
            }
        }
        return number;
    }


    private  T handleException(SupplierTypeExceptionHandler<? extends Object> exceptionHandler) {
        T result = null;
        try {
            result = (T)exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
