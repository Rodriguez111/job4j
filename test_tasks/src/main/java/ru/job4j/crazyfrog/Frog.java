package ru.job4j.crazyfrog;

import java.util.*;

public class Frog {
    private static final int SECTORS = 16;
    private static final int SEGMENTS = 10;

    private Queue<TreeSet<Integer>> segments = new LinkedList<>();
    private TreeSet<Integer> listOfIndex = new TreeSet<>();

    private int[] field = new int[SECTORS * SEGMENTS];


    /**
     *
     * @return array of points of the frog's shortest way to finish.
     */
    public int[] findAnyWays(int startIndex, int finishIndex, List<Integer> barriers) {
        field[finishIndex] = -3;
       for (Integer eachBarrier : barriers) {
           field[eachBarrier] = -1;
       }

        listOfIndex.add(startIndex);
        segments.add(listOfIndex); //добавляем первый элемент с начальной позицией лягушки
        int countOfSteps = 0;

        while (!segments.isEmpty()) {
            countOfSteps++;
          TreeSet<Integer> tempListOfIndex = segments.poll();
            listOfIndex = new TreeSet<>();
            for (Integer frogIndex : tempListOfIndex) {

                //задаем возможные направления и проверяем не выходят ли они за пределы по высоте массива
                int indexNearLeft = frogIndex + SEGMENTS - 2 >= frogIndex - frogIndex % SEGMENTS + SEGMENTS ? frogIndex + SEGMENTS - 2 : -1;
                int indexNearRight = frogIndex + SEGMENTS + 2 < frogIndex - frogIndex % SEGMENTS + SEGMENTS * 2 ? frogIndex + SEGMENTS + 2 : -1;
                int indexMiddleLeft = frogIndex + SEGMENTS * 2 - 1 >= frogIndex - frogIndex % SEGMENTS + SEGMENTS * 2 ? frogIndex + SEGMENTS * 2 - 1 : -1;
                int indexMiddleRight = frogIndex + SEGMENTS * 2 + 1 < frogIndex - frogIndex % SEGMENTS + SEGMENTS * 3 ? frogIndex + SEGMENTS * 2 + 1 : -1;
                int indexFarAway = frogIndex + SEGMENTS * 3;

                //проверяем не выходят ли за пределы по ширине массива
                indexNearLeft = outOfFieldLengthCheck(indexNearLeft);
                indexNearRight = outOfFieldLengthCheck(indexNearRight);
                indexMiddleLeft = outOfFieldLengthCheck(indexMiddleLeft);
                indexMiddleRight = outOfFieldLengthCheck(indexMiddleRight);
                indexFarAway = outOfFieldLengthCheck(indexFarAway);

                //проверяем, можно ли прыгнуть и не финиш ли.
                if (checkStep(indexNearLeft, countOfSteps, finishIndex)
                || checkStep(indexNearRight, countOfSteps, finishIndex)
                || checkStep(indexMiddleLeft, countOfSteps, finishIndex)
                || checkStep(indexMiddleRight, countOfSteps, finishIndex)
                || checkStep(indexFarAway, countOfSteps, finishIndex)) {
                    break;
                }

            }

            if (!listOfIndex.isEmpty()) {
                segments.add(listOfIndex);
            }
        }
        System.out.println("Шагов: " + field[finishIndex]);
        return findShortWay(startIndex, finishIndex);
    }


    private int outOfFieldLengthCheck(int position) {
        int result = -1;
        if (position != -1) {
            result =  position > field.length - 1 ? position - field.length : position;
        }
        return result;
    }

    private boolean checkStep(int stepIndex, int stepCount, int finish) {
        boolean result = false;
        if (stepIndex != -1 && (field[stepIndex] == 0 || field[stepIndex] == -3)) {
            field[stepIndex] = stepCount;
            listOfIndex.add(stepIndex);
            if (stepIndex == finish) {
                result = true;
            }
        }
       return result;
    }

    private int[] invertArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        return array;
    }


    private int[] findShortWay(int startIndex, int finishIndex) {
        int stepsAmount = field[finishIndex];
        int[] steps = new int[stepsAmount + 1];
        steps[0] = finishIndex;
        int counter = 1;
        for (int i = stepsAmount - 1; i > 0; i--) {
            for (int j = field.length - 1; j >= 0; j--) {
                if (field[j] == i && stepValidate(steps[counter - 1], j)) {
                    steps[counter++]  = j;
                    break;
                }
            }
        }
        steps[stepsAmount] = startIndex;
        return invertArray(steps);
    }


    private boolean stepValidate(int previousPosition, int currentPosition) {
        boolean result = false;
        int diff = previousPosition - currentPosition;
        diff = Math.abs(diff);
        if (diff ==  SEGMENTS - 2
            || diff ==  SEGMENTS + 2
            || diff ==  SEGMENTS * 2 - 1
            || diff ==  SEGMENTS * 2 + 1
            || diff == SEGMENTS * 3
            || field.length - diff ==  SEGMENTS - 2
            || field.length - diff ==  SEGMENTS + 2
            || field.length - diff ==  SEGMENTS * 2 - 1
            || field.length - diff ==  SEGMENTS * 2 + 1
            || field.length - diff == SEGMENTS * 3) {
            result = true;
        }
        return result;
    }
}