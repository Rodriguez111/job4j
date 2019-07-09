package ru.job4j.dijkstraalgorithm2;

import ru.job4j.dijkstraalgorithm2.Node;

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
        Node[][] boardOfNodes = new Node[board.length][board[0].length];
        Set<Node> set = new TreeSet<>();
        Node finishNode = null;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                Node node = new Node(x, y, board[y][x]);
                if (x == sx && y == sy) {
                    node.setWayToHereCost(0);
                }
                if (x == fx && y == fy) {
                    finishNode = node;
                }
                boardOfNodes[y][x] = node;
                set.add(node);
            }
        }

        while (set.size() > 0) {
            Iterator<Node> it = set.iterator();
            Node current = it.next();
            if (current.getX() == fx && current.getY() == fy) {
                break;
            }
            boolean leftNeighborExists = false;
            boolean rightNeighborExists = false;
            boolean upNeighborExists = false;
            boolean downNeighborExists = false;

            if ((current.getX() + 1 >= 0 && current.getX() + 1 < boardOfNodes[0].length) && (current.getY() >= 0 && current.getY() < boardOfNodes.length)) {
                rightNeighborExists = true;
            }
            if ((current.getX() - 1 >= 0 && current.getX() - 1 < boardOfNodes[0].length) && (current.getY() >= 0 && current.getY() < boardOfNodes.length)) {
                leftNeighborExists = true;
            }
            if ((current.getX() >= 0 && current.getX() < boardOfNodes[0].length) && (current.getY() - 1 >= 0 && current.getY() - 1 < boardOfNodes.length)) {
                upNeighborExists = true;
            }
            if ((current.getX() >= 0 && current.getX() < boardOfNodes[0].length) && (current.getY() + 1 >= 0 && current.getY() + 1 < boardOfNodes.length)) {
                downNeighborExists = true;
            }

            for (int y = 0; y < boardOfNodes.length; y++) {
                for (int x = 0; x < boardOfNodes[0].length; x++) {
                    if (leftNeighborExists) {
                        Node leftNeighbor = boardOfNodes[y][x];
                        int lastSegment = Math.abs(leftNeighbor.getValue() - current.getValue());
                        if (current.getX() - 1 == leftNeighbor.getX() && current.getY() == leftNeighbor.getY()) {
                            if (lastSegment + current.getWayToHereCost() < leftNeighbor.getWayToHereCost() || leftNeighbor.getWayToHereCost() == -1) {
                                boardOfNodes[y][x].setWayToHereCost(lastSegment + current.getWayToHereCost());
                                boardOfNodes[y][x].setFrom(current);
                            }
                        }
                    }
                    if (rightNeighborExists) {
                        Node rightNeighbor = boardOfNodes[y][x];
                        int lastSegment = Math.abs(rightNeighbor.getValue() - current.getValue());
                        if (current.getX() + 1 == rightNeighbor.getX() && current.getY() == rightNeighbor.getY()) {
                            if (lastSegment + current.getWayToHereCost() < rightNeighbor.getWayToHereCost() || rightNeighbor.getWayToHereCost() == -1) {
                                boardOfNodes[y][x].setWayToHereCost(lastSegment + current.getWayToHereCost());
                                boardOfNodes[y][x].setFrom(current);
                            }
                        }
                    }
                    if (upNeighborExists) {
                        Node upNeighbor = boardOfNodes[y][x];
                        int lastSegment = Math.abs(upNeighbor.getValue() - current.getValue());
                        if (current.getX() == upNeighbor.getX() && current.getY() - 1 == upNeighbor.getY()) {
                            if (lastSegment + current.getWayToHereCost() < upNeighbor.getWayToHereCost() || upNeighbor.getWayToHereCost() == -1) {
                                boardOfNodes[y][x].setWayToHereCost(lastSegment + current.getWayToHereCost());
                                boardOfNodes[y][x].setFrom(current);
                            }
                        }
                    }
                    if (downNeighborExists) {
                        Node downNeighbor = boardOfNodes[y][x];
                        int lastSegment = Math.abs(downNeighbor.getValue() - current.getValue());
                        if (current.getX() == downNeighbor.getX() && current.getY() + 1 == downNeighbor.getY()) {
                            if (lastSegment + current.getWayToHereCost() < downNeighbor.getWayToHereCost() || downNeighbor.getWayToHereCost() == -1) {
                                boardOfNodes[y][x].setWayToHereCost(lastSegment + current.getWayToHereCost());
                                boardOfNodes[y][x].setFrom(current);
                            }
                        }
                    }
                }
            }
            set.remove(current);
        }
        return finishNode.getWayToHereCost();
    }
}
