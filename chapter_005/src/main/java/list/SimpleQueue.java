package list;

public class SimpleQueue<T> {
    SimpleStack<T> stack1 = new SimpleStack<>();
    SimpleStack<T> stack2 = new SimpleStack<>();


    public void push(T item) {
        stack1.push(item);
        stack2.push(stack1.poll());
    }

    public T poll() {
       return stack2.poll();
    }


    public static void main(String[] args) {

        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);


        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }


}
