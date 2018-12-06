package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int range) throws MenuOutException {
        boolean exists = false;
        int key = Integer.parseInt(scanner.nextLine());
        if (key > 0 && key <= range) {
            exists = true;
        }
        if (!exists) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }


}
