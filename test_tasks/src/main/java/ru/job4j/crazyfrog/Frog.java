package ru.job4j.crazyfrog;

import java.util.*;

public class Frog {
    private static final int SECTORS = 16;
    private static final int SEGMENTS = 10;

    private Queue<TreeSet<Integer>> segments = new LinkedList<>();
    private TreeSet<Integer> listOfIndex = new TreeSet<>();
    private Random random = new Random();
    private int[] field = new int[SECTORS * SEGMENTS];

    private int frogIndex = setUnit(-2);
    private int barrier1Index = setUnit(-1);
    private int barrier2Index = setUnit(-1);
    private int finishIndex = setUnit(-3);

    public static void main(String[] args) {
        Frog frog = new Frog();
        System.out.printf("Старт: %d, Финиш: %d, Препятствия: %d, %d. \n", frog.frogIndex, frog.finishIndex, frog.barrier1Index, frog.barrier2Index);
       int[] result = frog.findAnyWays();

        for (int eachSegment : result) {
            System.out.println(eachSegment);
        }
    }

    /**
     *
     * @param value - value to assign to element.
     * @return - index of the element
     */
    private int setUnit(int value) {
        boolean isNotSet = true;
        int index = 0;
        while (isNotSet) {
            index =  random.nextInt(SECTORS * SEGMENTS);
            if (field[index] == 0) {
                field[index] = value;
                isNotSet = false;
            }
        }
        return index;
    }


    public int[] findAnyWays() {
        listOfIndex.add(frogIndex);
        segments.add(listOfIndex); //добавляем первый элемент с начальной позицией лягушки
        int countOfSteps = 0;
        boolean finishReached = false;

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
                if (checkStep(indexNearLeft, countOfSteps)
                || checkStep(indexNearRight, countOfSteps)
                || checkStep(indexMiddleLeft, countOfSteps)
                || checkStep(indexMiddleRight, countOfSteps)
                || checkStep(indexFarAway, countOfSteps)) {
                    break;
                }

            }

            if (finishReached) {
                break;
            }
            if (!listOfIndex.isEmpty()) {
                segments.add(listOfIndex);
            }
        }
        System.out.println("Шагов: " + field[finishIndex]);
        return findShortWay();
    }


    private int outOfFieldLengthCheck(int position) {
        int result = -1;
        if (position != -1) {
            result =  position > field.length - 1 ? position - field.length : position;
        }
        return result;
    }

    private boolean checkStep(int stepIndex, int stepCount) {
        boolean result = false;
        if (stepIndex != -1 && (field[stepIndex] == 0 || field[stepIndex] == -3)) {
            field[stepIndex] = stepCount;
            listOfIndex.add(stepIndex);
            if (stepIndex == finishIndex) {
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


    private int[] findShortWay() {
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
        steps[stepsAmount] = frogIndex;
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