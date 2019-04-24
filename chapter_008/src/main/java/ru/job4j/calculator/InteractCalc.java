package ru.job4j.calculator;

import ru.job4j.advancedcalculator.AdvancedCalcMenu;

import java.util.*;
import java.util.function.BiFunction;


public class InteractCalc {
    private final CalcMenu menu;
    private double result;
    private boolean isWorking = true;
    private Input input;

    public InteractCalc(Input input, CalcMenu menu) {
        this.input = input;
        this.menu = menu;
        this.input.setMenu(this.menu);
    }


    public void startCalc() {
        double first;
        BiFunction<Double, Double, Double> operation;
        double second;

        while (isWorking) {
            menu.printMenu();
            String symbol = input.getOperation("Input operation:");
            if (symbol.equals("exit")) {
                isWorking = false;
                break;
            }
            operation = menu.getOperation(symbol);
            Optional<Double> digitValue1 = input.getFirstValue("Enter first value:");
            first = digitValue1.orElseGet(() -> result);
            second = input.getSecondValue("Enter second value:");
            this.result = operation.apply(first, second);
            printResult(first, second, symbol);
        }
    }

    private void printResult(double first, double second, String symbol) {
        System.out.println(first + " " + symbol + " " + second + " = " + result);
        System.out.println("===========================");
    }


    public static void main(String[] args) {
        //InteractCalc interactCalc = new InteractCalc(new ConsoleInput(System.in), new CalcMenu());
        InteractCalc interactCalc = new InteractCalc(new ConsoleInput(System.in), new AdvancedCalcMenu());
        interactCalc.startCalc();
    }


}
