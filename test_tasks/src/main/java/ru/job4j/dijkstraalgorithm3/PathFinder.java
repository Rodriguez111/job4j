package ru.job4j.dijkstraalgorithm3;

import ru.job4j.tree.Tree;

import java.util.*;

/**
 * Задан двухмерных массив. Массив заполнен числами. По массиву двигается робот. Робот может двигаться вниз, вверх, влево и вправо.
 * Задача начальная точка и конечная. Перемещение из одной ктелки в другую затрачивает энергацию.
 * Рассчитывается она разность модуля значений клетов. Например, ход из клетки 1 в 10 будет оцениваться в 9 единиц.
 * Необходимо написать метод, который определяет наименее трудозатратный путь. *
 * int optimalWay(int[][] board, int sx, int sy, int fx, int fy)
 * <p>
 * [1, 2, 3]
 * [1, 3, 6]
 * [1, 1, 5]
 * start (0, 0), finish (3, 3). Ответ: 4. Путь 1 1 1 1 5
 */

public class PathFinder {

    public int optimalWay(int[][] board, int sx, int sy, int fx, int fy) {
        Map<Node, Integer> map = new HashMap<>();
        Map<Node, Integer> tempMap = new HashMap<>();
        Node startNode = new Node(sx, sy, board[sy][sx]);
        Node finishNode = new Node(fx, fy, board[fy][fx]);

        map.put(startNode, 0);
        tempMap.put(startNode, 0);

        while (tempMap.size() > 0) {
            Node current = tempMap.keySet().stream().findFirst().get();
            boolean leftNeighborExists = false;
            boolean rightNeighborExists = false;
            boolean upNeighborExists = false;
            boolean downNeighborExists = false;

            if ((current.getX() - 1 >= 0 && current.getX() - 1 < board[0].length) && (current.getY() >= 0 && current.getY() < board.length)) {
                leftNeighborExists = true;
            }
            if ((current.getX() + 1 >= 0 && current.getX() + 1 < board[0].length) && (current.getY() >= 0 && current.getY() < board.length)) {
                rightNeighborExists = true;
            }
            if ((current.getX() >= 0 && current.getX() < board[0].length) && (current.getY() - 1 >= 0 && current.getY() - 1 < board.length)) {
                upNeighborExists = true;
            }
            if ((current.getX() >= 0 && current.getX() < board[0].length) && (current.getY() + 1 >= 0 && current.getY() + 1 < board.length)) {
                downNeighborExists = true;
            }

            if (leftNeighborExists) {
                Node leftNeighbor = new Node(current.getX() - 1, current.getY(), board[current.getY()][current.getX() - 1]);
                int lastSegment = Math.abs(leftNeighbor.getValue() - current.getValue());
                map.computeIfPresent(leftNeighbor, (k, v) -> {
                    int result = v;
                    if (map.get(current) + lastSegment < v) {
                        result = map.get(current) + lastSegment;
                        tempMap.put(leftNeighbor, result);
                    }
                    return result;
                });
                map.computeIfAbsent(leftNeighbor, (k) -> {
                    int result = lastSegment + tempMap.get(current);
                    tempMap.put(leftNeighbor, result);
                    return result;
                });
            }
            if (rightNeighborExists) {
                Node rightNeighbor = new Node(current.getX() + 1, current.getY(), board[current.getY()][current.getX() + 1]);
                int lastSegment = Math.abs(rightNeighbor.getValue() - current.getValue());
                map.computeIfPresent(rightNeighbor, (k, v) -> {
                    int result = v;
                    if (map.get(current) + lastSegment < v) {
                        result = map.get(current) + lastSegment;
                        tempMap.put(rightNeighbor, result);
                    }
                    return result;
                });
                map.computeIfAbsent(rightNeighbor, (k) -> {
                    int result = lastSegment + tempMap.get(current);
                    tempMap.put(rightNeighbor, result);
                    return result;
                });
            }
            if (upNeighborExists) {
                Node upNeighbor = new Node(current.getX(), current.getY() - 1, board[current.getY() - 1][current.getX()]);
                int lastSegment = Math.abs(upNeighbor.getValue() - current.getValue());
                map.computeIfPresent(upNeighbor, (k, v) -> {
                    int result = v;
                    if (map.get(current) + lastSegment < v) {
                        result = map.get(current) + lastSegment;
                        tempMap.put(upNeighbor, result);
                    }
                    return result;
                });
                map.computeIfAbsent(upNeighbor, (k) -> {
                    int result = lastSegment + tempMap.get(current);
                    tempMap.put(upNeighbor, result);
                    return result;
                });
            }
            if (downNeighborExists) {
                Node downNeighbor = new Node(current.getX(), current.getY() + 1, board[current.getY() + 1][current.getX()]);
                int lastSegment = Math.abs(downNeighbor.getValue() - current.getValue());
                map.computeIfPresent(downNeighbor, (k, v) -> {
                    int result = v;
                    if (map.get(current) + lastSegment < v) {
                        result = map.get(current) + lastSegment;
                        tempMap.put(downNeighbor, result);
                    }
                    return result;
                });
                map.computeIfAbsent(downNeighbor, (k) -> {
                    int result = lastSegment + tempMap.get(current);
                    tempMap.put(downNeighbor, result);
                    return result;
                });
            }
            tempMap.remove(current);
        }
        return map.get(finishNode);
    }
}