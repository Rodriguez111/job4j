package ru.job4j.array;

/**
 * Merge two sorted arrays to one sorted.
 */

public class ArraysMerge {


    /**
     *
     * @param array1 - first sorted array.
     * @param array2 - second sorted array.
     * @return - merged sorted array from array1 and array2.
     */

    public int[] arraysMerging(int[] array1, int[] array2) {
        int[] resultArray = new int[array1.length + array2.length];
        int countForArray1 = 0;
        int countForArray2 = 0;

        for (int i = 0; i < resultArray.length; i++) {
            if (countForArray1 == array1.length) {
                addRest(resultArray, array2, i, countForArray2);
                break;
            } else if (countForArray2 == array2.length) {
                addRest(resultArray, array1, i, countForArray1);
                break;
           }

            if (array1[countForArray1] <= array2[countForArray2]) {
                resultArray[i] = array1[countForArray1];
                countForArray1++;
            } else {
                resultArray[i] = array2[countForArray2];
                countForArray2++;
            }

        }

     return resultArray;
    }


    /**
     * adding the rest values of the largest array to the end of result array.
     * @param arrayTo - result array where we need to add the rest values of the larger array.
     * @param arrayFrom - larger array, from where we need to take the rest values for the result array
     * @param indexTo - index of the result array from which we start adding values.
     * @param indexFrom - index of the larger array from which we start taking values.
     */
    private void addRest(int[] arrayTo, int[] arrayFrom, int indexTo, int indexFrom) {
        while (indexFrom < arrayFrom.length) {
            arrayTo[indexTo] = arrayFrom[indexFrom];
            indexTo++;
            indexFrom++;
        }

    }








}
