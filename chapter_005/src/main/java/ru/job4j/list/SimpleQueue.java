package ru.job4j.list;

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

}
