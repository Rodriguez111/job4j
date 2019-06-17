package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<E> implements Iterable<E> {
private final static int INITIAL_SIZE = 2;
E[] array = (E[]) new Object[INITIAL_SIZE];

private int size;
private int currentCapacity = array.length;
private int modCount;

public boolean add(E value) {
    if (size == array.length) {
       growUp();
    }
    array[size++] = value;
    modCount++;
    return true;
}

    public boolean addAll(Iterable<E> collection) {
        boolean result = false;
        for(E eachElement : collection) {
            result = add(eachElement);
        }
        return result;
    }

public E get(int index) {
    return array[index];
}


private void growUp() {
    currentCapacity = currentCapacity + currentCapacity / 2;
    array = Arrays.copyOf(array, currentCapacity);
}

    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }


    public int indexOf(E item) {
      int indexOf = -1;
    if (item == null) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                indexOf = i;
                break;
            }
        }
    } else {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                indexOf = i;
                break;
            }
        }
    }
    return indexOf;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }
}
