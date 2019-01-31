package list;

public class SimpleQueue<T> {
   private SimpleStack<T> input = new SimpleStack<>();
   private SimpleStack<T> output = new SimpleStack<>();


    public void push(T item) {
        input.push(item);
    }

    public T poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.poll());
            }
        }
       return output.poll();
    }

    public static void main(String[] args) {

        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        System.out.println(queue.poll());
        System.out.println(queue.poll());


        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


        queue.push(5);
        queue.push(6);
        queue.push(7);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }


}
