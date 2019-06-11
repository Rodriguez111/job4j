package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.function.Consumer;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;
    private final static int SLEEP = 50;
    private int step = 1;
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


    private void move(Consumer<Integer> consumer) {
        while (true) {
            if (borderReached()) {
                step = -step;
            }
            consumer.accept(step);
            sleep();
        }
    }

    private void moveHorizontal() {
        move((step) -> {this.rect.setX(this.rect.getX() + step);});
    }

    private void moveVertical() {
        move((step) -> {this.rect.setY(this.rect.getY() - step);});
    }

    private void moveMainDiagonal() {
        move((step) -> {this.rect.setX(this.rect.getX() + step);
        this.rect.setY(this.rect.getY() + step);
        });
    }

    private void moveSecondaryDiagonal() {
        move((step) -> {this.rect.setX(this.rect.getX() + step);
            this.rect.setY(this.rect.getY() - step);
        });
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean borderReached() {
        return rightBorderReached() || leftBorderReached() || bottomReached() || topReached();
    }
}
