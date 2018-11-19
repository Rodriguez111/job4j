package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean firstElement = data[0];
        for (boolean eachElement : data) {
            if (eachElement != firstElement) {
                result = false;
                break;
            }
        }
        return result;
    }
}
