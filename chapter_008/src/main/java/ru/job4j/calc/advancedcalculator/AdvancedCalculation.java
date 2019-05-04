package ru.job4j.calc.advancedcalculator;

public class AdvancedCalculation {
    /**
     * Contains the result of the operation
     */
    private double result;

    /**
     * addition
     * @param value - raising value
     * @param degree - the degree to which the value must be raised.
     */

    public void exp(double value, double degree) {
        this.result = value;
        for (int i = 1; i < degree; i++) {
            this.result = this.result * value;
        }
    }

    public void root(double value, double power) {
        result =  Math.exp(Math.log(value) / power);
    }

    public double getResult() {
        return result;
    }

}
