package ru.job4j.array;

/**
 * Leaves unique entries trims duplicates.
 */

import java.util.Arrays;

public class ArrayDuplicate {
    /**
     *
     * @param array - original array.
     * @return - trimmed array with unique entries.
     *
     * lastElementIndex - индекс последнего элемента, куда можно вставить найденный повтор.
     * currentElement - текущий проверяемый элемент.
     * if (i == lastElementIndex) - если дошли до блока сгруппированных в конце повторов - выходим, поиск завершен.
     * if (array[j].equals(currentElement)) - если текущий элемент равен следующему - меняем его местами с элементом с индексом lastElementIndex.
     * lastElementIndex-- - назначаем следующий с конца элемент, куда будем вставлять следующий найденный повтор.
     * i = -1 - начинаем просмотр массива с начала.
     */
    public String[] remove(String[] array) {
        int lastElementIndex = array.length - 1;
        String currentElement = "";
        for (int i = 0; i < array.length; i++) {
            if (i == lastElementIndex) {
                break;
            }
            currentElement = array[i];
            for (int j = i + 1; j <= lastElementIndex; j++) {
                if (array[j].equals(currentElement)) {
                    array[j] = array[lastElementIndex];
                    array[lastElementIndex] = currentElement;
                    lastElementIndex--;
                    i = -1;
                    break;
                }
            }
        }
        return Arrays.copyOf(array, lastElementIndex + 1);
    }
}
