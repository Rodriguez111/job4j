package list;

import java.util.NoSuchElementException;

public class SimpleUnidirectionalList<E> {
    private int size;
    private Node<E> first;

    /**
     * method add data to the beginning of the list
     * @param date
     */
    public void add(E date) {
        Node<E>  firstElement = new Node<>(date);
        firstElement.next = first;
        first = firstElement;
        size++;
    }

    /**
     * Get element by index.
     * @param index
     * @return
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    /**
     * Get collection size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Delete first element.
     */
    public E delete() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E firstElement = first.item;
        first = first.next;
        size--;
        return firstElement;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }


}
