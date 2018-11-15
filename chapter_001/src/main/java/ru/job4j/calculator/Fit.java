package ru.job4j.calculator;
/**
 * Perfect weight calculation.
 */

public class Fit {

    /**
     * Men's perfect weight.
     * @param height Height.
     * @return Perfect weight.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Women's perfect weight.
     * @param height Height.
     * @return Perfect weight.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }

}
