package ru.job4j.pseudo;

/**
 * Drawing triangle.
 */

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("     o     ");
        pic.append("\n");
        pic.append("    o o    ");
        pic.append("\n");
        pic.append("   o   o   ");
        pic.append("\n");
        pic.append("  o     o  ");
        pic.append("\n");
        pic.append(" o       o ");
        pic.append("\n");
        pic.append("ooooooooooo");
        return pic.toString();
    }
}
