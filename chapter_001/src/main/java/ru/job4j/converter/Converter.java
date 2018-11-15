package ru.job4j.converter;

/**
 * Currency converter.
 */
public class Converter {

    /**
     * Contains dollar rate
     */
    private int dollarRate = 60;

    /**
     * Contains euro rate
     */
    private int euroRate = 70;



    /**
     * Converting rubles to euro.
     * @param value rubles.
     * @return euro.
     */
    public int rubleToEuro(int value) {
        return value / euroRate;
    }

    /**
     * Converting rubles to dollars.
     * @param value rubles.
     * @return dollars.
     */
    public int rubleToDollar(int value) {
        return value / dollarRate;
    }

    /**
     * Converting euro to rubles.
     * @param value euro.
     * @return rubles.
     */
    public int euroToRuble(int value) {
        return value * euroRate;
    }

    /**
     * Converting dollars to rubles.
     * @param value dollars.
     * @return rubles.
     */
    public int dollarToRuble(int value) {
        return value * dollarRate;
    }





}
