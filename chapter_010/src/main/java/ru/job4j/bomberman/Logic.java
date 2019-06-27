package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class Logic extends Thread {
    private final Board board;
    private final Figure hero;


    public Logic(Board board, Figure hero) {
        this.board = board;
        this.hero = hero;
    }

    /**
     * Sets random start position for hero.
     * @return - true if position was selected, false if there is no free position
     */
    private boolean setHeroStartPosition() {
        boolean result = false;
        Optional<Cell> randomCell = board.getRandomLockObject();
        if (randomCell.isPresent()) {
            this.hero.setCurrentPosition(randomCell.get());
            result = true;
        }
        return result;
    }

    /**
     * Generating list of movements.
     * @return - list with movements.
     * @throws InterruptedException
     */
    private List<Supplier> generateListOfMovements() throws InterruptedException {
        List<Supplier> movements = new ArrayList<>();
        movements.add(() -> tryToMove(up()));
        movements.add(() -> tryToMove(down()));
        movements.add(() -> tryToMove(left()));
        movements.add(() -> tryToMove(right()));
        return movements;
    }

    @Override
    public void run() {
        if (setHeroStartPosition()) {
            while (!isInterrupted()) {
                try {
                    allMovements();
                    System.out.println(hero);
                    sleep(1000);
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " Hero " + hero.getName() + " finished.");
    }

    /**
     * Unlocks source cell and sets new cell for the hero.
     * @param source - cell where the hero was.
     * @param dest - cell where the hero goes.
     * @return true.
     */
    private boolean move(Cell source, Cell dest) {
        board.getLockObject(source).unlock();
        hero.setCurrentPosition(dest);
        return true;
    }

    /**
     * Trying to move randomly to any direction. If there is no way to move, interrupts thread.
     * @throws InterruptedException
     */
    private void allMovements() throws InterruptedException {
        List<Supplier> movements = generateListOfMovements();
        Random random = new Random();
        boolean result = false;
        while (movements.size() > 0 && !result) {
            int randIndex = random.nextInt(movements.size());
            result = movements.get(randIndex).apply();
            movements.remove(randIndex);
        }
        if (!result) {
            System.out.println(hero.getName() + " can not move");
            interrupt();
        }
    }

    /**
     * If passed cell exists, trying to occupy it.
     * @param cell - cell to occupy.
     * @return - true if managed to occupy cell.
     * @throws InterruptedException
     */
    private boolean tryToMove(Cell cell) throws InterruptedException {
        boolean result = false;
        if (board.cellExists(cell)) {
            ReentrantLock lockObject = board.getLockObject(cell);
            if (lockObject.tryLock(500, TimeUnit.MILLISECONDS)) {
                result = move(hero.getCurrentPosition(), cell);
            }
        }
        return result;
    }

    private Cell up() {
        return new Cell(hero.getCurrentPosition().getX(), hero.getCurrentPosition().getY() - 1);
    }

    private Cell down() {
        return new Cell(hero.getCurrentPosition().getX(), hero.getCurrentPosition().getY() + 1);
    }

    private Cell left() {
        return new Cell(hero.getCurrentPosition().getX() - 1, hero.getCurrentPosition().getY());
    }

    private Cell right() {
        return new Cell(hero.getCurrentPosition().getX() + 1, hero.getCurrentPosition().getY());
    }
}
