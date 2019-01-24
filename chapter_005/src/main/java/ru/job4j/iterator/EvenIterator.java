package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class EvenIterator implements Iterator {

    private  int[] array;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    private int index = 0;


    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
           if (array[i] % 2 == 0)  {
               result = true;
               index = i;
               break;
           }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }

}
