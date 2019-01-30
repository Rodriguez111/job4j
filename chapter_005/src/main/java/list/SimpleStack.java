package list;

public class SimpleStack<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public void push(E data) {
        Node<E> currentFirst = first;
        Node<E> newItem = new Node<>(data, null, currentFirst);
        first = newItem;
        if (currentFirst == null) {
            last = newItem;
        } else {
            currentFirst.previous = newItem;
        }
        modCount++;
        size++;
    }

    public E poll() {
        E result = null;
        if (first != null) {
            Node<E> resultNode = first;
            result  = resultNode.data;
            if (resultNode.next != null) {
                first = resultNode.next;
                first.previous = null;
            } else {
                first = null;
                last = null;
            }
            modCount++;
            size--;
        }
        return  result;
    }

    public E peek() {
        E result = null;
        if (first != null) {
            result = first.data;
        }
        return result;
    }

    private static class Node<E> {
        private E data;
        private Node<E> previous;
        private Node<E> next;

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }


}
