package list;

public class LoopSearch<T> {
   static boolean hasCycle(Node first) {
       boolean result = false;
       Node current = first;
       while (current.next != null) {
           if (current.label) {
               result = true;
               break;
           }
           current.label = true;
           current =  current.next;
       }
            return result;
    }

   static class Node<T> {
        public Node(T value) {
            this.value = value;
        }
        T value;
        boolean label = false;
        Node<T> next;
    }
}
