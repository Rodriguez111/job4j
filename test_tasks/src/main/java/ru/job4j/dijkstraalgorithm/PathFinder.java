package ru.job4j.dijkstraalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Задан двухмерных массив. Массив заполнен числами. По массиву двигается робот. Робот может двигаться вниз, вверх, влево и вправо.
 * Задача начальная точка и конечная. Перемещение из одной ктелки в другую затрачивает энергацию.
 * Рассчитывается она разность модуля значений клетов. Например, ход из клетки 1 в 10 будет оцениваться в 9 единиц.
 * Необходимо написать метод, который определяет наименее трудозатратный путь. *
 * int optimalWay(int[][] board, int sx, int sy, int fx, int fy)
 *
 * [1, 2, 3]
 * [1, 3, 6]
 * [1, 1, 5]
 * start (0, 0), finish (3, 3). Ответ: 4. Путь 1 1 1 1 5
 */

public class PathFinder {
    private Node[][] boardOfNodes;

    /**
     * List of all nodes, sorted in ascending order by value of shortest total distance to this point
     */
    private final List<Node> listOfNodes = new ArrayList<>();

    /**
     * Initiates list of nodes and board of nodes
     * @param board
     */
    private void init(int[][] board) {
        this.boardOfNodes = new Node[board.length][board[0].length];
        int x = 0;
        int y = 0;
        for (int[] eachRow : board) {
            for (int eachElement : eachRow) {
                Node node = new Node(x, y, board[y][x]);
                boardOfNodes[y][x] = node;
                listOfNodes.add(node);
                x++;
            }
            x = 0;
            y++;
        }
    }

    /**
     * Sets start point.
     * @param x - x coordinate.
     * @param y - y coordinate.
     */
    private void setStartNode(int x, int y) {
        for (Node eachElement : this.listOfNodes) {
            if (eachElement.getX() == x && eachElement.getY() == y) {
                eachElement.setWayToHereCost(0);
                break;
            }
        }
    }

    /**
     * Sorts list of nodes in ascending order and pull of thr minimal value.
     * @return
     */
    private Node pullElement() {
        Node element = null;
        if (listOfNodes.size() > 0) {
            sortNodes();
            element = listOfNodes.get(0);
            listOfNodes.remove(0);
        }
        return element;
    }

    /**
     * Find neighbour node on top, on bottom, on left, on right, and if exists,
     * calculates distance to them.
     * @param node - node to determine neighbours for.
     */
    private void findNear(Node node) {
        if (nodeExists(node.getX() + 1, node.getY())) {
            calculateDistanceTo(node, getNodeFromBoard(node.getX() + 1, node.getY()));
        }
        if (nodeExists(node.getX() - 1, node.getY())) {
            calculateDistanceTo(node, getNodeFromBoard(node.getX() - 1, node.getY()));
        }
        if (nodeExists(node.getX(), node.getY() + 1)) {
            calculateDistanceTo(node, getNodeFromBoard(node.getX(), node.getY() + 1));
        }
        if (nodeExists(node.getX(), node.getY() - 1)) {
            calculateDistanceTo(node, getNodeFromBoard(node.getX(), node.getY() - 1));
        }
    }

    /**
     * Find and return node, matching coordinates.
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return = node with x and y coordinates.
     */
    private Node getNodeFromBoard(int x, int y) {
        Node node = null;
        for (Node[] eachRow : boardOfNodes) {
            for (Node eachElement : eachRow) {
                if (eachElement.getX() == x && eachElement.getY() == y) {
                    node = eachElement;
                    break;
                }
            }
            if (node != null) {
                break;
            }
        }
        return node;
    }

    /**
     *
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return true if node with this coordinates exists.
     */
    private boolean nodeExists(int x, int y) {
        return (x >= 0 && x < this.boardOfNodes[0].length) && (y >= 0 && y < this.boardOfNodes.length);
    }

    /**
     *  Calculating total cost of the way from the begining of the way through current node to the next node.
     * @param thisNode - current node.
     * @param thatNode - next node.
     */
    private void calculateDistanceTo(Node thisNode, Node thatNode) {
        int wayCost = Math.abs(thisNode.getValue() - thatNode.getValue());
        int summaryCost = wayCost + thisNode.getWayToHereCost();
        if (summaryCost < thatNode.getWayToHereCost() || thatNode.getWayToHereCost() == -1) {
            thatNode.setFrom(thisNode);
            thatNode.setWayToHereCost(summaryCost);
        }
    }

    /**
     * Sorts list of nodes in ascending order by value of total cost of the way from the begining to this node.
     */
    private void sortNodes() {
        listOfNodes.sort((node1, node2) -> {
            int result = 0;
            if (node1.getWayToHereCost() == -1 && node2.getWayToHereCost() != -1) {
                result = 1;
            }
            if (node1.getWayToHereCost() != -1 && node2.getWayToHereCost() == -1) {
                result = -1;
            }
            if (node1.getWayToHereCost() != -1 && node2.getWayToHereCost() != -1) {
                result = Integer.compare(node1.getWayToHereCost(), node2.getWayToHereCost());
            }
            return result;
        });
    }

    /**
     * Calculates minimal cost of the way from start to end.
     * @param board - field with nodes.
     * @param sx - x coordinate for the start point.
     * @param sy  - y coordinate for the start point.
     * @param fx - x coordinate for the finish point.
     * @param fy - y coordinate for the finish point.
     * @return - total minimal cost of the way from start to end.
     */
    public int optimalWay(int[][] board, int sx, int sy, int fx, int fy) {
        init(board);
        setStartNode(sx, sy);
        Node finishNode = getNodeFromBoard(fx, fy);
        while (listOfNodes.size() > 0) {
            Node currentNode = pullElement();
            if (currentNode.equals(finishNode)) {
                break;
            }
            findNear(currentNode);
        }
        return finishNode.getWayToHereCost();
    }

    /**
     * Collects to list values of the nodes from which the cheapest route consists of.
     * @param startX  - x coordinate for the start point.
     * @param startY  - y coordinate for the start point.
     * @param finishX - x coordinate for the finish point.
     * @param finishY - y coordinate for the finish point.
     * @return - list of values of the nodes from which the cheapest route consists of.
     */
    public List<Integer> pickPath(int startX, int startY, int finishX, int finishY) {
        Node start = getNodeFromBoard(startX, startY);
        Node end = getNodeFromBoard(finishX, finishY);
        List<Integer> result = new ArrayList<>();
        Node currentNode = end;
        while (!currentNode.equals(start)) {
            result.add(currentNode.getValue());
            currentNode = currentNode.getFrom();
        }
        result.add(currentNode.getValue());
        Collections.reverse(result);
        return result;
    }
}
