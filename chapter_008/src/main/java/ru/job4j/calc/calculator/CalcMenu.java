package ru.job4j.calc.calculator;


import ru.job4j.calculator.Calculator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CalcMenu {
    private Map<String, CalcOperation> menu = new LinkedHashMap<>();
    private final Calculator calculator = new Calculator();

    public CalcMenu() {
        initMenu();
    }

    private void initMenu() {
        addMenuItem("+", "Add", (a, b) -> {
            calculator.add(a, b);
            return calculator.getResult();
        });

        addMenuItem("-", "Subtract", (a, b) -> {
            calculator.subtract(a, b);
            return calculator.getResult();
        });

        addMenuItem("*", "Multiple", (a, b) -> {
            calculator.multiple(a, b);
            return calculator.getResult();
        });

        addMenuItem("/", "Divide", (a, b) -> {
            calculator.div(a, b);
            return calculator.getResult();
        });

        addMenuItem("exit", "Exit", null);
    }


    /**
     * This method is for enhancing simple calculator menu with additional menu items.
     * @param operationSymbol - symbol of added operation.
     * @param operationDescription - operation description.
     * @param biFunction - corresponding function.
     */
    public void addMenuItem(String operationSymbol, String operationDescription, BiFunction<Double, Double, Double> biFunction) {
        int id = operationSymbol.equals("exit") ? Integer.MAX_VALUE : generateItemId();
        menu.put(operationSymbol, new CalcOperation(operationSymbol, operationDescription, biFunction, id));
        sortMenu();
    }

    public void printMenu() {
        System.out.println("Choose operation:");
        for (Map.Entry<String, CalcOperation> m : menu.entrySet()) {
            System.out.println(m.getValue());
        }
    }

    public String getAllMenuSymbols() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, CalcOperation> m : menu.entrySet()) {
            sb.append("\"" + m.getKey() + "\", ");
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    public BiFunction<Double, Double, Double> getOperation(String symbol) {
        return menu.get(symbol).getOperation();
    }

    public boolean isValid(String symbol) {
        return menu.containsKey(symbol);
    }

    private int generateItemId() {
        return menu.size() > 0 ? menu.size() : 0;
    }

    private void sortMenu() {
        this.menu = menu.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

}
