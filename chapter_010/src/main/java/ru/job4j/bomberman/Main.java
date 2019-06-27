package ru.job4j.bomberman;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Figure hero1 = new Hero("Hero1");
        Figure hero2 = new Hero("Hero2");
        Figure hero3 = new Hero("Hero3");
        Board board = new Board(5, 3);

        Logic logic1 = new Logic(board, hero1);
        Logic logic2 = new Logic(board, hero2);
        Logic logic3 = new Logic(board, hero3);

        logic1.start();
        logic2.start();
        logic3.start();

        Thread.sleep(5000);
        logic1.interrupt();
        logic2.interrupt();
        logic3.interrupt();

    }

}
