package ru.job4j.set;


import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    SimpleArrayList<E> array = new SimpleArrayList<>();

public boolean add(E item) {
    boolean result = false;
    if (!array.contains(item)) {
        array.add(item);
        result = true;
    }
    return result;
}




    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

//    public static void main(String[] args) {
//        SimpleSet<String> simpleSet = new SimpleSet();
//        simpleSet.add("one");
//        simpleSet.add("two");
//        simpleSet.add("three");
//        simpleSet.add("one");
//
//        for (String e : simpleSet) {
//            System.out.println(e);
//        }
//
//    }




}
