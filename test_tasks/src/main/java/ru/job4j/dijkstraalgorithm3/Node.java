package ru.job4j.dijkstraalgorithm3;

import java.util.Objects;

public class Node {
    private int x;
    private int y;
    private int value;



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



    public int getValue() {
        return value;
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
