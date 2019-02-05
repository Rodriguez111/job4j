package ru.job4j.tree;

import java.util.*;

public class CustomTree<E extends Comparable<E>> implements SimpleTree<E> {
   Node<E> root;
   int size = 1;

    public CustomTree(E root) {
        this.root = new Node<E>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean elementAdded = false;
        if (child != null) {
            Optional<Node<E>> duplicate = findBy(child);
            if (duplicate.isEmpty()) {
                Optional<Node<E>> parentFound = findBy(parent);
                if (parentFound.isPresent()) {
                    parentFound.get().add(new Node<>(child));
                    elementAdded = true;
                    size++;
                }
            }
        }
        return elementAdded;
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
        Queue<Node<E>> tree = new LinkedList<>();
        int expectedSize = size;
            {
                if (root != null) {
                    tree.offer(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !tree.isEmpty();
            }

            @Override
            public E next() {
                if (expectedSize != size) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> element = tree.poll();
                List<Node<E>> list = element.leaves();
                if (list != null) {
                    for (Node<E> eachChild : list) {
                        tree.offer(eachChild);
                    }
                }
                return element.getValue();
            }
        };
    }

}
