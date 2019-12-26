package ru.job4j.bomberman2;

import javafx.util.Pair;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;

    public Board(int width, int height) {
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
            move(0, 0);

        }, "1");

        Thread hero2 = new Thread(() -> {
            move(board[0].length - 1, board.length - 1);

        }, "2");
        hero1.start();
        hero2.start();

        try {
            Thread.sleep(10000);
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

    private void move(int startX, int startY) {
        board[startY][startX].lock();
        ReentrantLock previous = board[startY][startX];
        while (!Thread.currentThread().isInterrupted()) {
            Random random = new Random();
            int result = random.nextInt(4);
            ReentrantLock newPrevious;
            switch (result) {
                case 0:
                    newPrevious = moveLeft(previous);
                    if (newPrevious != null) {
                        previous = newPrevious;
                    }

                case 1:
                    newPrevious = moveRight(previous);
                    if (newPrevious != null) {
                        previous = newPrevious;
                    }

                case 2:
                    newPrevious = moveUp(previous);
                    if (newPrevious != null) {
                        previous = newPrevious;
                    }

                case 3:
                    newPrevious = moveDown(previous);
                    if (newPrevious != null) {
                        previous = newPrevious;
                    }

                default:

            }
        }
    }

    private ReentrantLock moveLeft(ReentrantLock previous) {
        Pair<Integer, Integer> current = findCoordinates(previous);
        int currentX = current.getKey();
        int currentY = current.getValue();
        ReentrantLock newPosition = null;
        if (leftExists(currentX)) {
            if (tryBlock(currentX - 1, currentY, previous)) {
                newPosition = board[currentY][currentX - 1];
            }
        }
        return newPosition;
    }

    private ReentrantLock moveRight(ReentrantLock previous) {
        Pair<Integer, Integer> current = findCoordinates(previous);
        int currentX = current.getKey();
        int currentY = current.getValue();
        ReentrantLock newPosition = null;
        if (rightExists(currentX)) {
            if (tryBlock(currentX + 1, currentY, previous)) {
                newPosition = board[currentY][currentX + 1];
            }
        }
        return newPosition;
    }

    private ReentrantLock moveUp(ReentrantLock previous) {
        Pair<Integer, Integer> current = findCoordinates(previous);
        int currentX = current.getKey();
        int currentY = current.getValue();
        ReentrantLock newPosition = null;
        if (upExists(currentY)) {
            if (tryBlock(currentX, currentY - 1, previous)) {
                newPosition = board[currentY - 1][currentX];
            }
        }
        return newPosition;
    }

    private ReentrantLock moveDown(ReentrantLock previous) {
        Pair<Integer, Integer> current = findCoordinates(previous);
        int currentX = current.getKey();
        int currentY = current.getValue();
        ReentrantLock newPosition = null;
        if (downExists(currentY)) {
            if (tryBlock(currentX, currentY + 1, previous)) {
                newPosition = board[currentY + 1][currentX];
            }

        }
        return newPosition;
    }

    private Pair<Integer, Integer> findCoordinates(ReentrantLock reentrantLock) {
        Pair<Integer, Integer> result = null;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (reentrantLock.equals(board[y][x])) {
                    result = new Pair<Integer, Integer>(x, y);
                    break;
                }
            }
        }
        return result;
    }

    private boolean leftExists(int x) {
        return x - 1 >= 0 && x - 1 < board[0].length;
    }

    private boolean rightExists(int x) {
        return x + 1 >= 0 && x + 1 < board[0].length;
    }

    private boolean upExists(int y) {
        return y - 1 >= 0 && y - 1 < board.length;
    }

    private boolean downExists(int y) {
        return y + 1 >= 0 && y + 1 < board.length;
    }

    public static void main(String[] args) {
        Board board = new Board(4, 2);
        board.move();
    }
}