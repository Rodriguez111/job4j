package ru.job4j.fafa;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private final InputStream inputStream;

    public Input(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int getValue() {
        System.out.println("Input positive integer value");
        int value;
        while (true) {
            try {
                Scanner scanner = new Scanner(inputStream);
                value = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("This is not an integer value");
            }
        }
        return value;
    }

}
