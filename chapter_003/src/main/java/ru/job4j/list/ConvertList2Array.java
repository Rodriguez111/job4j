package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {

    /**
     *
     * @param list - list which converts to array.
     * @param rows - amount of rows in array.
     * @return - result array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows + list.size() % rows;
        int[][] resultArray = new int[rows][cells];
        int countRow = 0;
        int countCell = 0;
        for (Integer eachInteger : list) {
            resultArray[countRow][countCell] = eachInteger;
            countCell++;
            if (countCell == cells) {
                countCell = 0;
                countRow++;
            }
        }
        return resultArray;
    }
}
