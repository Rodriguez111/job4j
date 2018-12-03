package ru.job4j.pseudo;

/**
 * Drawing triangle.
 */

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        pic.append("     o     ");
        pic.append(lineSeparator);
        pic.append("    o o    ");
        pic.append(lineSeparator);
        pic.append("   o   o   ");
        pic.append(lineSeparator);
        pic.append("  o     o  ");
        pic.append(lineSeparator);
        pic.append(" o       o ");
        pic.append(lineSeparator);
        pic.append("ooooooooooo");
        return pic.toString();
    }
}
