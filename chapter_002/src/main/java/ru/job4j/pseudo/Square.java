package ru.job4j.pseudo;
/**
 * Drawing square.
 */

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("oooooooo");
        pic.append("\n");
        pic.append("0      0");
        pic.append("\n");
        pic.append("0      0");
        pic.append("\n");
        pic.append("oooooooo");
        return pic.toString();
    }
}
