package ru.job4j.condition;

/**
 * Triangle square determination.
 */

public class Triangle {
    private Point a;
    private Point b;
    private Point c;


    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    /**
     * @return Semiperimeter of triangle.
     */
    public double semiPerimeter(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     *
     * @return true if triangle exists.
     */

    public boolean exists(double ab, double ac, double bc) {
        if ((ab + ac) > bc && (ab + bc) > ac && (ac + bc) > ab) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return area of triangle.
     */

    public double area() {
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        if (exists(ab, ac, bc)) {
            double semiPerimeter = semiPerimeter(ab, ac, bc);
            return Math.sqrt(semiPerimeter * (semiPerimeter - ab) * (semiPerimeter - ac) * (semiPerimeter - bc));
        }
        return -1.0;
    }

}
