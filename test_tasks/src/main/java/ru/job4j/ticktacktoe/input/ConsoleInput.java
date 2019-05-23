package ru.job4j.ticktacktoe.input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput implements Input {


    @Override
    public int inputCoordinate(String ask) {
        System.out.println(ask);
        int result;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                result = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input error, not an integer");
            }
        }
        return result;
    }
}
