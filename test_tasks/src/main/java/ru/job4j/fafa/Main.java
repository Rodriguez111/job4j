package ru.job4j.fafa;

public class Main {
    public static void main(String[] args) {
        Input input = new Input(System.in);
        Fafa fafa = new Fafa();
        int result = fafa.calculateVariants(input.getValue());
        System.out.println("Result is: " + result);
    }
}
