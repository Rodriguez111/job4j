package generic;

import java.util.Iterator;

public class SimpleArray<T>  implements Iterable<T>{
   final int amountOfElements;

    public SimpleArray(int amountOfElements) {
        this.amountOfElements = amountOfElements;
    }

//    public boolean add(T model) {
//
//    }
//
//
//    public boolean set(int index, T model) {
//
//    }
//
//    public boolean remove(int index) {
//
//    }
//
//    public T get (int index) {
//
//    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
