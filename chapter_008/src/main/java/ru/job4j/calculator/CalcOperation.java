package ru.job4j.calculator;

import java.util.function.BiConsumer;

public class CalcOperation {
    private String itemSymbol;
    private String itemDescription;
    private BiConsumer<Double, Double> operation;

    public CalcOperation(String itemSymbol, String itemDescription, BiConsumer<Double, Double> operation) {
        this.itemSymbol = itemSymbol;
        this.itemDescription = itemDescription;
        this.operation = operation;
    }

    public BiConsumer<Double, Double> getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return this.itemSymbol + " - " + itemDescription;
    }
}
