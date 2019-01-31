/**
 * Задан связанный список.
 *
 * class Node<T> {
 *    T value;
 *    Node<T> next;
 * }
 *
 * Node first = new Node(1);
 * Node two = new Node(2);
 * Node third = new Node(3);
 * Node four = new Node(4);
 *
 * first.next = two;
 * two.next = third;
 * third.next = four;
 * four.next = first;
 *
 * Написать алгоритм определяющий, что список содержит замыкания.
 *
 * boolean hasCycle(Node first);
 *
 * Обратите внимание, что список может быть замкнут и в середине. К примеру, 3-й узел будет ссылаться на 2-й узел.
 * Определение зацикленности необходимо реализовать путем прохода по узлам, без использования коллекций.
 *
 * Решение с пом. алгоритма черепахи и зайца
 */
package list;

public class LoopSearch<T> {
   static boolean hasCycle(Node first) {
       Node slow = first;
       Node fast = first;
       boolean result = false;
       if (slow.next != null && fast.next.next != null) {
           while (fast.next != null && fast.next.next != null) {
               slow =  slow.next;
               fast = fast.next.next;
               if (slow.equals(fast)) {
                   result = true;
                   break;
               }
           }
       }
            return result;
    }

   static class Node<T> {
        public Node(T value) {
            this.value = value;
        }
        T value;
        Node<T> next;
    }

}
