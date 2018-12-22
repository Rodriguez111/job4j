package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Functions {



    public List<Double> diapason(int start, int finish, java.util.function.Function<Double, Double> function) {
        List<Double> list = new ArrayList<>();
        for(double i = start; i <= finish; i++) {
            list.add(function.apply(i));
        }
        return list;
    }


//    public static void main(String[] args) {
//        Functions functions = new Functions();
//
//        List<Double> linear =   functions.diapason(0, 3, (value) -> {
//            double coefficient = 2;
//            double  result = coefficient * value;
//            return result;
//        });
//
//        List<Double> quadratic =   functions.diapason(0, 3, (value) -> {
//            return value * value;
//        });
//
//
//        List<Double> logarithm =   functions.diapason(0, 3, (value) -> {
//            Double  result = null;
//            if(value > 0) {
//                result = Math.log(value);
//            }
//            return result;
//        });
//
//
//        for (Double eachDouble : linear) {
//            System.out.println(eachDouble);
//        }
//        System.out.println("--------");
//        for (Double eachDouble : quadratic) {
//            System.out.println(eachDouble);
//        }
//
//        System.out.println("--------");
//        for (Double eachDouble : logarithm) {
//            System.out.println(eachDouble);
//        }
//
//    }


}
