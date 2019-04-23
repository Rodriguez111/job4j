package ru.job4j.calculator;

import java.util.*;
import java.util.function.BiConsumer;


public class InteractCalc {
    private final Calculator calculator = new Calculator();
    private double result;
    private final Map<String, CalcOperation> menu = new TreeMap<>();
    private boolean isWorking = true;
    private Input input;

    public InteractCalc(Input input) {
        initMenu();
        this.input = input;

    }

    private void initMenu() {
        menu.put("+", new CalcOperation("+",
                "Add",
                (a, b) -> {
                    calculator.add(a, b);
                    result = calculator.getResult();
                })
        );
        menu.put("-", new CalcOperation("-",
                "Subtract",
                (a, b) -> {
                    calculator.subtract(a, b);
                    result = calculator.getResult();
                })
        );
        menu.put("*", new CalcOperation("*",
                "Multiple",
                (a, b) -> {
                    calculator.multiple(a, b);
                    result = calculator.getResult();
                })
        );
        menu.put("/", new CalcOperation("/",
                "Divide",
                (a, b) -> {
                    calculator.div(a, b);
                    result = calculator.getResult();
                })
        );
        menu.put("exit", new CalcOperation("exit",
                        "Exit",
                        null
                )
        );
    }

    private void printMenu() {
        System.out.println("Choose operation:");
        for (Map.Entry<String, CalcOperation> m : menu.entrySet()) {
            System.out.println(m.getValue());
        }
    }


    public void startCalc() {
        double first;
        BiConsumer<Double, Double> operation;
        double second;

        while (isWorking) {
            printMenu();
            String symbol = input.getOperation("ConsoleInput operation:");
            if (symbol.equals("exit")) {
                isWorking = false;
                break;
            }
            operation = menu.get(symbol).getOperation();
            Optional<Double> digitValue1 = input.getFirstValue("Enter first value:");
            first = digitValue1.isPresent() ? digitValue1.get() : result;
            second = input.getSecondValue("Enter second value:");
            operation.accept(first, second);
            printResult(first, second, symbol);
        }
    }

    private void printResult(double first, double second, String symbol) {
        System.out.println(first + " " + symbol + " " + second + " = " + result);
        System.out.println("===========================");
    }


    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new ConsoleInput(System.in));
        interactCalc.startCalc();
    }


}
