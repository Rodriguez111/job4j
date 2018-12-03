package ru.job4j.pseudo;
/**
 * Drawing square.
 */

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        pic.append("oooooooo");
        pic.append(lineSeparator);
        pic.append("0      0");
        pic.append(lineSeparator);
        pic.append("0      0");
        pic.append(lineSeparator);
        pic.append("oooooooo");
        return pic.toString();
    }
}
