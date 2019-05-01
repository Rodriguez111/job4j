package ru.job4j.menu;

import java.util.Scanner;

public class ConsoleInput implements Input {

    @Override
    public String askUser(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
