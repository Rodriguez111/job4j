package ru.job4j.condition;

/**
 Class for calculating distance between two points
 * @author Rodriguez (mymail4java@gmail.com)
 * @version 1
 * @since 15.11.2018
 */

public class Point {

    /**
     * Contains point coordinate on x axis.
     */
    private int x;

    /**
     * Contains point coordinate on y axis.
     */
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance between two points.
     * @param that - another point.
     * @return - distance.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow((that.x - this.x), 2) + Math.pow((that.y - this.y), 2));
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);


        double result = a.distanceTo(b);
        System.out.println("Расстояние между A и B: " + result);
    }
}
