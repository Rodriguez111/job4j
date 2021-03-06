package ru.job4j.calc.calculator;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner;
    private CalcMenu menu;

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
            if (menu.isValid(result)) {
                isRight = true;
            } else {
                printWarning();
            }
        } while (!isRight);
        return result;
    }

   private void printWarning() {
       System.out.println("Incorrect operation, it must be " + menu.getAllMenuSymbols());
   }

    @Override
    public void setMenu(CalcMenu menu) {
        this.menu = menu;
    }
}
