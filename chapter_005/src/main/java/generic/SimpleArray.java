package generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
   private int amountOfElements;
   private int globalIndex = 0;


    public int getGlobalIndex() {
        return globalIndex;
    }

    private Object[] objectArray;
    T[] array;

    public SimpleArray(int amountOfElements) {
        this.amountOfElements = amountOfElements;
        objectArray = new Object[amountOfElements];
        array = (T[]) objectArray;
    }

    /**
     * Add element of type T to the first empty position.
     * @param model - element for insertion.
     * @return - operation result.
     */
    public boolean add(T model) {
        boolean isSuccessful = false;
            if (globalIndex < array.length) {
                array[globalIndex++] = model;
                isSuccessful = true;
            }
        return isSuccessful;
    }

    /**
     * Insert element to the position of index.
     * @param index - index of element to insert in.
     * @param model - element for insertion.
     * @return - operation result.
     */
     boolean set(int index, T model) {
        boolean isSuccessful = false;
        if (index < globalIndex) {
            array[index] = model;
            isSuccessful = true;
        }
        return isSuccessful;
    }


     boolean remove(int index) {
        boolean isSuccessful = false;
        if (index >= 0 && index < globalIndex) {
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, amountOfElements - index - 1);
            isSuccessful = true;
        }
        return isSuccessful;
    }

     T get(int index) {
        return array[index];
    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index >= 0 && index < globalIndex;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }



}
