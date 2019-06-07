package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

import java.util.*;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;
    private final Runnable runnable;
    private final Map<Directions, Runnable> movements = new HashMap<>();

    private void initMovements() {
        movements.put(Directions.HORIZONTAL, this::moveHorizontal);
        movements.put(Directions.VERTICAL, this::moveVertical);
        movements.put(Directions.MAIN_DIAGONAL, this::moveMainDiagonal);
        movements.put(Directions.SECONDARY_DIAGONAL, this::moveSecondaryDiagonal);
    }

    public RectangleMove(Rectangle rect, int limitX, int limitY, Directions direction) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
        initMovements();
        this.runnable = movements.get(direction);
    }

    @Override
    public void run() {
        runnable.run();
    }

    private boolean leftBorderReached() {
        return this.rect.getX() <= 0;
    }

    private boolean rightBorderReached() {
        return this.rect.getX() >= limitX - rect.getWidth();
    }

    private boolean topReached() {
        return this.rect.getY() <= 0;
    }

    private boolean bottomReached() {
        return this.rect.getY() >= limitY - rect.getHeight();
    }

    private void moveHorizontal() {
        int step = 1;
        while (true) {
            if (borderReached()) {
                step = -step;
            }
            this.rect.setX(this.rect.getX() + step);
            sleep(50);
        }
    }

    private void moveVertical() {
        int step = 1;
        while (true) {
            if (borderReached()) {
                step = -step;
            }
            this.rect.setY(this.rect.getY() - step);
            sleep(50);
        }
    }

    private void moveMainDiagonal() {
        int step = 1;
        while (true) {
            if (borderReached()) {
                step = -step;
            }
            this.rect.setX(this.rect.getX() + step);
            this.rect.setY(this.rect.getY() + step);
            sleep(50);
        }
    }

    private void moveSecondaryDiagonal() {
        int step = 1;
        while (true) {
            if (borderReached()) {
                step = -step;
            }
            this.rect.setX(this.rect.getX() + step);
            this.rect.setY(this.rect.getY() - step);
            sleep(50);
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean borderReached() {
        return rightBorderReached() || leftBorderReached() || bottomReached() || topReached();
    }
}
