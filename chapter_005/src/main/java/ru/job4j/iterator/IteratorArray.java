package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorArray implements Iterator {
    private int[][] array;

    public IteratorArray(int[][] array) {
        this.array = array;
    }

    private int generalIndex = 0;
    private int heightIndex = 0;
    private int widthIndex = 0;

    @Override
    public boolean hasNext() {
        return generalIndex < arraySize();
    }

    @Override
    public Object next() {
        int result = 0;
        checkOneDimensionalArrayBound();
        result = array[heightIndex][widthIndex++];
        generalIndex++;
        return result;
    }

    int arraySize() {
        int arraySize = 0;
        for (int i = 0; i < array.length; i++) {
            arraySize = arraySize + array[i].length;
        }
        return arraySize;
    }

    void checkOneDimensionalArrayBound() {
        boolean boundIsReached = false;
        if (array[heightIndex].length == widthIndex) {
            heightIndex++;
            widthIndex = 0;
        }
    }
}
