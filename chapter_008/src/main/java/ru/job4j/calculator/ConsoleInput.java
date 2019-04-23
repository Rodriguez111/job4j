package ru.job4j.calculator;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner;


    public ConsoleInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Optional<Double> getFirstValue(String question) {
        Optional<Double> value = Optional.empty();
        System.out.println(question);

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                break;
            }
            try {
                value = Optional.of(Double.valueOf(line));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entered value is not a double");
            }
        }
        return value;
    }

    public double getSecondValue(String question) {
        double value;
        System.out.println(question);
        while (true) {
            try {
                value = Double.valueOf(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entered value is not a double");
            }
        }
        return value;
    }

    public String getOperation(String question) {
        System.out.println(question);
        String result;
        boolean isRight = false;
        do {
            result = scanner.nextLine();
            if (result.equals("+") || result.equals("-") || result.equals("*") || result.equals("/") || result.equals("exit")) {
                isRight = true;
            } else {
                System.out.println("Incorrect operation, it must be \"+\", \"-\", \"*\", \"/\" or \"exit\" ");
            }
        } while (!isRight);
        return result;
    }

}
