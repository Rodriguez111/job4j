package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConvertList2Array {

    /**
     *
     * @param list - list which converts to array.
     * @param rows - amount of rows in array.
     * @return - result array
     */
    public int[][] toArray(List<Integer> list, int rows) {
//        int cells = list.size() / rows + list.size() % rows;
//        int[][] resultArray = new int[rows][cells];
//        int countRow = 0;
//        int countCell = 0;
//        for (Integer eachInteger : list) {
//            resultArray[countRow][countCell] = eachInteger;
//            countCell++;
//            if (countCell == cells) {
//                countCell = 0;
//                countRow++;
//            }
//        }
//        return resultArray;

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
        List<Integer> resultList = new ArrayList<>();
        for (int[] eachArray : list) {
            for (int eachInt : eachArray) {
                resultList.add(eachInt);
            }
        }
        return resultList;
    }






}
