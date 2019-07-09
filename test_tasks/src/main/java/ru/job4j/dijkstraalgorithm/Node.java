package ru.job4j.dijkstraalgorithm;

import java.util.Objects;

public class Node {
    private int x;
    private int y;
    private int value;
    private Node from;
    private int wayToHereCost = -1;


    public Node(int x, int y, int nodeCost) {
        this.x = x;
        this.y = y;
        this.value = nodeCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public int getValue() {
        return value;
    }

    public void setWayToHereCost(int wayToHereCost) {
        this.wayToHereCost = wayToHereCost;
    }

    public Node getFrom() {
        return from;
    }

    public int getWayToHereCost() {
        return wayToHereCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return x == node.x
                && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
