package ru.job4j.dijkstraalgorithm2;

import java.util.Comparator;
import java.util.Objects;

public class Node implements Comparable<Node> {
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
    public int compareTo(Node o) {
        int result = 0;

        if (this.wayToHereCost == -1 && o.wayToHereCost != -1) {
            result = 1;
        }

        if (this.wayToHereCost != -1 && o.wayToHereCost == -1) {
            result = -1;
        }

        if (o.wayToHereCost != -1 && this.wayToHereCost != -1) {
            result = Integer.compare(this.wayToHereCost, o.wayToHereCost);
        }

        if (result == 0) {
            result = Integer.compare(this.x, o.x);
        }
        if (result == 0) {
            result = Integer.compare(this.y, o.y);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return true;
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
