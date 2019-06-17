package ru.job4j.threadsafelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeList<E> implements Iterable<E> {

   @GuardedBy("this")
   private final SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();

    public ThreadSafeList() {
    }

    public ThreadSafeList(final SimpleArrayList<E> simpleArrayList) {
        this.simpleArrayList.addAll(simpleArrayList);
    }

    public synchronized boolean add(final E value) {
        return simpleArrayList.add(value);
    }

    public synchronized E get(final int index) {
        return simpleArrayList.get(index);
    }

    public synchronized int indexOf(final E item) {
        return simpleArrayList.indexOf(item);
    }

    public boolean contains(final E item) {
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
