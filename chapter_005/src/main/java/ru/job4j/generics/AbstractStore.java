package ru.job4j.generics;

import ru.job4j.generics.exceptions.ElementNotFoundException;

import java.util.Iterator;
import java.util.Optional;

public abstract class AbstractStore<T extends Base> implements Store<T>, Iterable<T> {
    SimpleArray<T> baseArray;

    public AbstractStore(int size) {
        this.baseArray = new SimpleArray(size);
    }

    @Override
    public void add(T model) {
        baseArray.add(model);
    }

    @Override
    public  boolean replace(String id, T model) {
        boolean success = false;
       for (int i = 0; i < baseArray.getGlobalIndex(); i++) {
           if (baseArray.get(i).getId().equals(id)) {
               baseArray.set(i, model);
               success = true;
               break;
           }
       }
       return success;
    }

    @Override
    public boolean delete(String id) {
        boolean success = false;
        for (int i = 0; i < baseArray.getGlobalIndex(); i++) {
            if (baseArray.get(i).getId().equals(id)) {
                baseArray.remove(i);
                success = true;
                break;
            }
        }
        return success;
    }

    @Override
    public  T findById(String id) {
        Optional<T> result = Optional.empty();
        for (int i = 0; i < baseArray.getGlobalIndex(); i++) {
            if (baseArray.get(i).getId().equals(id)) {
                result = Optional.of(baseArray.get(i));
                break;
            }
        }
        if (!result.isPresent()) {
            throw new ElementNotFoundException("Element not found");
        }
        return result.get();
    }

    @Override
    public Iterator<T> iterator() {
        return baseArray.iterator();
    }
}
