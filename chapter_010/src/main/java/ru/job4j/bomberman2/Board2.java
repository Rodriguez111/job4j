package ru.job4j.bomberman2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board2 {
    private final ReentrantLock[][] board;

    public Board2(int width, int height) {
        this.board = new ReentrantLock[height][width];
        initBoard();
    }

    private void initBoard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = new ReentrantLock();
            }
        }
    }

    public void move() {
        Thread hero1 = new Thread(() -> {
            moveRandom(0, 0);
        }, "1");

        Thread hero2 = new Thread(() -> {
            moveRandom(board[0].length - 1, board.length - 1);
        }, "2");
        hero1.start();
        hero2.start();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hero1.interrupt();
        hero2.interrupt();
    }

    private boolean tryBlock(int x, int y, ReentrantLock previous) {
        boolean canLock = true;
        try {
            if (!board[y][x].equals(previous)) {
                if (board[y][x].tryLock(500, TimeUnit.MILLISECONDS)) {
                    previous.unlock();
                    System.out.println("Hero " + Thread.currentThread().getName() + " moved " + y + "-" + x);
                } else {
                    canLock = false;
                }
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return canLock;
    }

    private void moveRandom(int fromX, int fromY) {
        board[fromY][fromX].lock();
        Random random = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            ReentrantLock current = board[fromY][fromX];
            int vector = random.nextInt(4);
            int newX = vector == 0 ? fromX + 1 : vector == 1 ? fromX - 1 : fromX;
            int newY = vector == 2 ? fromY + 1 : vector == 3 ? fromY - 1 : fromY;
            if (canGoHere(newX, newY)) {
                if (tryBlock(newX, newY, current)) {
                    fromX = newX;
                    fromY = newY;
                }
            }
        }
    }

    private boolean canGoHere(int x, int y) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
    }

    public static void main(String[] args) {
        Board2 board = new Board2(4, 2);
        board.move();
    }
}