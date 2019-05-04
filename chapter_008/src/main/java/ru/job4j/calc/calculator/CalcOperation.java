package ru.job4j.calc.calculator;


import java.util.function.BiFunction;

public class CalcOperation implements Comparable<CalcOperation> {
    private int id;
    private String itemSymbol;
    private String itemDescription;
    private BiFunction<Double, Double, Double> operation;

    public CalcOperation(String itemSymbol, String itemDescription, BiFunction<Double, Double, Double> operation, int id) {
        this.itemSymbol = itemSymbol;
        this.itemDescription = itemDescription;
        this.operation = operation;
        this.id = id;
    }

    public BiFunction<Double, Double, Double> getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return this.itemSymbol + " - " + itemDescription;
    }

    @Override
    public int compareTo(CalcOperation o) {
        return Integer.compare(this.id, o.id);
    }
}
