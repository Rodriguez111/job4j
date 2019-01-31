package list;

import java.util.*;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    /**
     * Add element to the end of the list
     * @param item - item to add
     */
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

    /**
     * Get element by the index.
     * @param index - index of the element.
     * @return element by the index.
     */
    public E get(int index) {
        checkIndex(index);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Delete element by the index.
     * @param index - index of the element.
     * @return result of the operation.
     */
    public boolean delete(int index) {
        checkIndex(index);
        Node<E> toDelete = first;
        for (int i = 0; i < index; i++) {
            toDelete = toDelete.next;
        }
        if (size == 1) {
            deleteSingle();
        } else if (toDelete.previous == null) {
            first = toDelete.next;
            first.previous = null;
        } else if (toDelete.next == null) {
            last = toDelete.previous;
            toDelete.next = null;
        } else {
            toDelete.previous.next = toDelete.next;
            toDelete.next.previous = toDelete.previous;
        }
        size--;
        modCount--;
        return true;
    }

    /**
     * Delete last standing element.
     */
    public void deleteSingle() {
        first = null;
        last = null;
        size--;
        modCount--;
    }


    /**
     * Add element by the index.
     * @param index - index of the element.
     * @param item - item to add.
     * @return result of the operation.
     */
    public boolean add(int index, E item) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            add(item);
        } else {
            Node<E> currentElement = first;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            Node<E> newElement = new Node<>(currentElement.previous, currentElement, item);
            currentElement.previous = newElement;
            newElement.previous.next = newElement;
            size++;
            modCount++;
        }
            return true;
    }

    /**
     * Add element to the first position (index = 0).
     * @param item  - item to add.
     * @return result of the operation.
     */
    public boolean addFirst(E item) {
        Node<E> currentFirst = first;
        Node<E> newItem = new Node<>(null, currentFirst, item);
        first = newItem;
        if (currentFirst == null) {
            last = newItem;
        } else {
            currentFirst.previous = newItem;
        }
        modCount++;
        size++;
        return true;
    }


    /**
     * Checks if index is not out of bound of the list.
     * @param index - index to check.
     */
    private void checkIndex(int index) {
        if (first == null ||  size < index) {
            throw new NoSuchElementException();
        }
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

    public int getSize() {
        return size;
    }

}
