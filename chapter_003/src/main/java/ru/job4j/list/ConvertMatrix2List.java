package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ConvertMatrix2List<T> {

    /**
     *
     * @param array - array which we convert to list.
     * @return - result list.
     */
    public List<Integer> toList(int[][] array) {
//        List<Integer> list = new ArrayList<>();
//        for (int[] eachRow : array) {
//            for (int eachColumn : eachRow) {
//                list.add(eachColumn);
//            }
//        }
//        return list;
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }



}
