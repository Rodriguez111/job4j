package calculator;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public void multiply(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> biFunction,
                         Consumer<Double> consumer) {
        for (int i = start; i != finish; i++) {
            consumer.accept(biFunction.apply(value, i));
        }
    }


//    public static void main(String[] args) {
//        calculator calculator = new calculator();
//        calculator.multiply(0, 10, 2,
//                (value, index) -> {
//           double result = value * index;
//           return result ;
//                }, result -> System.out.println(result));
//    }
}
