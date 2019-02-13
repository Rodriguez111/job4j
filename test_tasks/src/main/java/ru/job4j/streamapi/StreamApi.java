package ru.job4j.streamapi;

/**
 * Задан массив чисел. *
 * 1. Нужно него отфильтровать, оставить только четные числа. *
 * 2. Каждое число возвести в квадрат. *
 * 3. И все элементы просуммировать.
 */

import java.util.Arrays;
import java.util.OptionalInt;

public class StreamApi {

public int filter(int[] array) {
    int resultInt = -1;

    OptionalInt result = Arrays.stream(array).filter(every -> every % 2 == 0).map(every -> every * every).reduce((d1, d2) -> d1 + d2);
    if (result.isPresent()) {
        resultInt = result.getAsInt();
    }

   return resultInt;
}
}
