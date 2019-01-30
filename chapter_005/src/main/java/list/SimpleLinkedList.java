package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;


    public void add(E item) {
        final Node<E> currentLast = last;
        Node<E> newItem = new Node<>(currentLast, null, item);
        last = newItem;
        if (currentLast == null) {
            first = newItem;
        } else {
            currentLast.next = newItem;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }







    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;
            int currentModCount = modCount;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (currentModCount != modCount) {
                   throw new ConcurrentModificationException();
                }
                return get(index++);
            }
        };
    }

    private static class Node<E> {
        private Node<E> previous;
        private Node<E> next;
        E data;

        public Node(Node<E> previous, Node<E> next, E data) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }
    }

//    public static void main(String[] args) {
//        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//
//
//
//        for (Integer e : list ) {
//            if(e == 2) {
//                list.add(15);
//            }
//            System.out.println(e);
//        }
//
//
//    }


}
