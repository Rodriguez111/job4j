package calculator;

public class MathUtil {
    public static double add(int first, int second) {
        return first + second;
    }

    public static double multiply(int first, int second) {
        return first * second;
    }

    public static double divide(int first, int second) {
        return first / second;
    }



    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.multiply(0, 10, 2,
                MathUtil::add, System.out::println);
    }
}
