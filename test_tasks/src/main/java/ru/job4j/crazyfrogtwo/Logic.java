package ru.job4j.crazyfrogtwo;
import java.util.*;
public class Logic {
    private static final int SECTORS = 16;
    private static final int SEGMENTS = 10;
    private Map<Integer, Cell> field = new HashMap<>();
    private void initField(int start, int finish, List<Integer> barriers) {
        for (int i = 0; i < SECTORS; i++) {
            for (int j = 0; j < SEGMENTS; j++) {
                int counter = i * SEGMENTS + j;
                Cell cell = new Cell(i, j);
                if (!barriers.stream().filter(barrier -> barrier == counter).findFirst().equals(Optional.empty())) {
                    cell.setValue(-1);
                }
                field.put(counter, cell);
            }
        }
        setField();
    }

    private void setField() {
        for (Map.Entry<Integer, Cell> field : field.entrySet()) {
            Cell currentCell = field.getValue();

            if (currentCell.getSegment() > SEGMENTS - 3) {
                currentCell.setHasNearLeft(false);
            }
            if (currentCell.getSegment() > SEGMENTS - 2) {
                currentCell.setHasMiddleLeft(false);
            }
            if (currentCell.getSegment() < 2) {
                currentCell.setHasNearRight(false);
            }
            if (currentCell.getSegment() < 1) {
                currentCell.setHasMiddleRight(false);
            }
            if (currentCell.hasNearLeft()) {
            int indexNearLeft = outOfFieldLengthCheck(field.getKey() + SEGMENTS + 2);
              currentCell.setNearLeft(this.field.get(indexNearLeft));
            }
            if (currentCell.hasNearRight()) {
            int indexNearRight = outOfFieldLengthCheck(field.getKey() + SEGMENTS - 2);
                currentCell.setNearRight(this.field.get(indexNearRight));
            }
            if (currentCell.hasMiddleLeft()) {
                int indexMiddleLeft = outOfFieldLengthCheck(field.getKey() + SEGMENTS * 2 + 1);
                currentCell.setMiddleLeft(this.field.get(indexMiddleLeft));
            }
            if (currentCell.hasMiddleRight()) {
                int indexMiddleRight = outOfFieldLengthCheck(field.getKey() + SEGMENTS * 2 - 1);
                currentCell.setMiddleRight(this.field.get(indexMiddleRight));
            }
            if (currentCell.hasFarAway()) {
                int indexFarAway = outOfFieldLengthCheck(field.getKey() + SEGMENTS * 3);
                currentCell.setFarAway(this.field.get(indexFarAway));
            }
        }
    }

    private int outOfFieldLengthCheck(int position) {
        int result =  position > field.size() - 1 ? position - field.size() : position;
        return result;
    }

    public Cell[] findWay(int start, int finish, List<Integer> barriers) {
        initField(start, finish, barriers);
        Queue<Cell> segments = new LinkedList<>();
        Cell startCell = field.get(start);
        int startValue = 1;
        int finishValue = 0;
        startCell.setValue(startValue);
        segments.add(startCell);
        while (!segments.isEmpty()) {
          Cell currentCell = segments.poll();
          if (currentCell.equals(field.get(finish))) {
              finishValue = currentCell.getValue();
              break;
          }
            if (currentCell.hasNearLeft() && currentCell.getNearLeft().getValue() == 0) {
                currentCell.getNearLeft().setValue(currentCell.getValue() + 1);
                segments.add(currentCell.getNearLeft());
            }
            if (currentCell.hasNearRight() && currentCell.getNearRight().getValue() == 0) {
                currentCell.getNearRight().setValue(currentCell.getValue() + 1);
                segments.add(currentCell.getNearRight());
            }
            if (currentCell.hasMiddleLeft() && currentCell.getMiddleLeft().getValue() == 0) {
                currentCell.getMiddleLeft().setValue(currentCell.getValue() + 1);
                segments.add(currentCell.getMiddleLeft());
            }
            if (currentCell.hasMiddleRight() && currentCell.getMiddleRight().getValue() == 0) {
                currentCell.getMiddleRight().setValue(currentCell.getValue() + 1);
                segments.add(currentCell.getMiddleRight());
            }
            if (currentCell.hasFarAway() && currentCell.getFarAway().getValue() == 0) {
                currentCell.getFarAway().setValue(currentCell.getValue() + 1);
                segments.add(currentCell.getFarAway());
            }
        }

        int[] steps = new int[finishValue];
        steps[0] = finish;
        int count = 1;
        for (int i = steps.length; i > 0; i--) {
            for (Map.Entry<Integer, Cell> eachCell : field.entrySet()) {
                if (eachCell.getValue().getValue() == i && stepValidate(steps[count - 1], eachCell.getKey())) {
                    steps[count++] = eachCell.getKey();
                    break;
                }
            }
        }
        return indexesToCells(steps);
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
                || field.size() - diff ==  SEGMENTS - 2
                || field.size() - diff ==  SEGMENTS + 2
                || field.size() - diff ==  SEGMENTS * 2 - 1
                || field.size() - diff ==  SEGMENTS * 2 + 1
                || field.size() - diff == SEGMENTS * 3) {
            result = true;
        }
        return result;
    }

    private Cell[] indexesToCells(int[] indexes) {
        Cell[] result = new Cell[indexes.length];
        for (int i = indexes.length - 1; i >= 0; i--) {
            result[indexes.length - 1 - i] = field.get(indexes[i]);
        }
        return result;
    }

    public Map<Integer, Cell> getField() {
        return field;
    }
}
