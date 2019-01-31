package list;

public class SimpleStack<E> {
  private SimpleLinkedList<E> simpleLinkedList = new SimpleLinkedList<>();

    public void push(E data) {
        simpleLinkedList.addFirst(data);
    }

    public E poll() {
     E result = null;
     if (simpleLinkedList.getSize() > 0) {
         result = simpleLinkedList.get(0);
         simpleLinkedList.delete(0);
     }
        return result;
    }

    public E peek() {
      return simpleLinkedList.getSize() > 0 ? simpleLinkedList.get(0) : null;
    }

}
