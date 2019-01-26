package generic;

import java.util.Iterator;

public class SimpleArray<T>  implements Iterable<T>{
   private int amountOfElements;


    private Object[] objectArray;
    T[] array;

    public SimpleArray(int amountOfElements) {
        this.amountOfElements = amountOfElements;
        objectArray = new Object[amountOfElements];
        array = (T[])objectArray;
    }

    /**
     * Add element of type T to the first empty position.
     * @param model - element for insertion.
     * @return - operation result.
     */
    public boolean add(T model) {
        boolean isSuccessful = false;
        for(int i = 0; i < amountOfElements; i++) {
            if(array[i] == null) {
                array[i] = model;
                isSuccessful = true;
                break;
            }
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
        if(index < amountOfElements) {
            array[index] = model;
            isSuccessful = true;
        }
        return isSuccessful;
    }


     boolean remove(int index) {
        boolean isSuccessful = false;
        if(index < amountOfElements && array[index] != null) {
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, amountOfElements - index - 1);
            isSuccessful = true;
        }
        return isSuccessful;
    }

     T get (int index) {
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }
}
