package ru.job4j.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ConvertMatrix2List<T> {

    /**
     *
     * @param array - array which we convert to list.
     * @return - result list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] eachRow : array) {
            for (int eachColumn : eachRow) {
                list.add(eachColumn);
            }
        }
        return list;
    }



}
