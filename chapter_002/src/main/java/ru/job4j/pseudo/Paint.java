package ru.job4j.pseudo;

public class Paint {
Shape shape;
public void setShape(Shape shape) {
    this.shape = shape;
}

public void executeShape() {
    System.out.println(shape.draw());
}

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.setShape(new Square());
        paint.executeShape();

        paint.setShape(new Triangle());
        paint.executeShape();
    }



}
