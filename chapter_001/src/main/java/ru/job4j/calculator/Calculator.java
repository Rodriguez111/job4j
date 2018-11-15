package ru.job4j.calculator;
/**

Class for calculating arithmetic operations
 * @author Rodriguez (mymail4java@gmail.com)
 * @version 1
 * @since 15.11.2018
 */


public class Calculator {

    /**
     * Contains the result of the operation
     */
    private double result;

    /**
     * addition
     * @param first - first term
     * @param second - second term
     */

   public void add(double first, double second) {
       this.result = first + second;
   }

    /**
     * subtraction
     * @param first - subtrahend
     * @param second - subtract
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * division
     * @param first - dividend
     * @param second - divider
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiplication
     * @param first - first multiplier
     * @param second - second multiplier
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
       return this.result;
    }



}