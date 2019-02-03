package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ConvertMatrix2List<T> {

    /**
     *
     * @param array - array which we convert to ru.job4j.list.
     * @return - result ru.job4j.list.
     */
    public List<Integer> toList(int[][] array) {
//        List<Integer> ru.job4j.list = new ArrayList<>();
//        for (int[] eachRow : array) {
//            for (int eachColumn : eachRow) {
//                ru.job4j.list.add(eachColumn);
//            }
//        }
//        return ru.job4j.list;
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }



}
