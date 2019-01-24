package ru.job4j.iterator;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
        Iterator<Integer> currentIteratorOfIterators = it.next();
        int currentInteger;


            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                while (it.hasNext() && !currentIteratorOfIterators.hasNext()) {
                    currentIteratorOfIterators = it.next();
                }
                if(currentIteratorOfIterators.hasNext()) {
                    hasNext = true;
                }

                return hasNext;


            }

            @Override
            public Integer next() {

                if (currentIteratorOfIterators.hasNext()) {
                    return currentIteratorOfIterators.next();
                } else if (it.hasNext()) {
                    currentIteratorOfIterators = it.next();
                    return currentIteratorOfIterators.next();
                }
                   throw new NoSuchElementException();
            }
        };
    }
}
