package ru.job4j.threadsafelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeList<E> implements Iterable<E> {

   @GuardedBy("this")
   private final SimpleArrayList<E> simpleArrayList;

    public ThreadSafeList(SimpleArrayList<E> simpleArrayList) {
        this.simpleArrayList = simpleArrayList;
    }


    public synchronized boolean add(E value) {
        return simpleArrayList.add(value);
    }

    public synchronized E get(int index) {
        return simpleArrayList.get(index);
    }

    public synchronized int indexOf(E item) {
        return simpleArrayList.indexOf(item);
    }

    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }

    public synchronized int size() {
        return simpleArrayList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return copy().iterator();
    }

    private Iterable copy() {
        SimpleArrayList<E> list = new SimpleArrayList<>();
        synchronized (this) {
            for (E eachElement : this.simpleArrayList) {
                list.add(eachElement);
            }
        }
       return list;
    }
}
