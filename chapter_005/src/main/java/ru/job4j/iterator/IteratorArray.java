package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorArray implements Iterator {
    private int[][] array;

    public IteratorArray(int[][] array) {
        this.array = array;
    }

    private int heightIndex = 0;
    private int widthIndex = 0;

    @Override
    public boolean hasNext() {
        return widthIndex < array[heightIndex].length || heightIndex < array.length - 1;
    }

    @Override
    public Object next() {
        int result = 0;
        checkOneDimensionalArrayBound();
        result = array[heightIndex][widthIndex++];

        return result;
    }

    void checkOneDimensionalArrayBound() {
        boolean boundIsReached = false;
        if (array[heightIndex].length == widthIndex) {
            heightIndex++;
            widthIndex = 0;
        }
    }
}
