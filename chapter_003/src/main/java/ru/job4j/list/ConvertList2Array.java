package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertList2Array {

    /**
     *
     * @param list - ru.job4j.list which converts to array.
     * @param rows - amount of rows in array.
     * @return - result array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        Iterator<Integer> iterator = list.iterator();
        int cls = list.size() / rows + (list.size() % rows == 0 ? 0 : 1);

        int[][] array = new int[rows][cls];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cls; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                }
            }
        }
        return array;


    }

    public List<Integer> convert(List<int[]> list) {
     return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }






}
