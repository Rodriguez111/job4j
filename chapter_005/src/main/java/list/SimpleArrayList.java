package list;

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

public E get(int index) {
    return array[index];
}


private void growUp() {
    currentCapacity = currentCapacity + currentCapacity / 2;
    array = Arrays.copyOf(array, currentCapacity);
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
