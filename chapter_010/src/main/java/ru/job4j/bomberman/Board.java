package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    /**
     * Returns ReentrantLock object which corresponds to the cell coordinates.
     *
     * @param cell - the cell of which must match the desired lock object.
     * @return - ReentrantLock object, corresponding to cell.
     */
    public ReentrantLock getLockObject(Cell cell) {
        boolean isFound = false;
        ReentrantLock result = null;
        if (cellExists(cell)) {
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (cell.getX() == x && cell.getY() == y) {
                        result = board[y][x];
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Selects, occupies and locks a random cell field.
     *
     * @return = selected random cell.
     */
    public synchronized Optional<Cell> getRandomLockObject() {
        List<Cell> list = new ArrayList<>();
        Optional<Cell> result = Optional.empty();
        boolean found = false;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (!board[y][x].isLocked()) {
                    board[y][x].lock();
                    list.add(new Cell(x, y));
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (list.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(list.size());
            result = Optional.of(list.get(index));
        }
        return result;
    }


    /**
     * Checks if cell located in the field.
     *
     * @param cell - cell to check.
     * @return = true, if cell is within the field area.
     */
    public boolean cellExists(Cell cell) {
        boolean result = true;
        if (cell.getY() < 0 || cell.getY() >= board.length) {
            result = false;
        }
        if (cell.getX() < 0 || cell.getX() >= board[0].length) {
            result = false;
        }
        return result;
    }
}
