package ru.job4j.iterator;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> current = it.next();
            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                if (current.hasNext()) {
                    hasNext = true;
                } else if (it.hasNext()) {
                    current = it.next();
                    if (current.hasNext()) {
                        hasNext = true;
                    }
                }
               return hasNext;
            }

            @Override
            public Integer next() {

                if (current.hasNext()) {
                    return current.next();
                } else if (it.hasNext()) {
                    current = it.next();
                    return current.next();
                }
                   throw new NoSuchElementException();
            }
        };
    }
}
