package ru.job4j.profiling;

import ru.job4j.tracker.Input;

public class OutOfMemoryInput implements Input {
  private final int count;
  private int counter;

    public OutOfMemoryInput(int count) {
        this.count = count;
    }

    @Override
    public String ask(String question) {
       return "" + counter;
    }

    @Override
    public int ask(String question, int range) {
        counter++;
        return counter < count ? 1 : 7;
    }
}
