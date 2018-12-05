package ru.job4j.tracker;

public class StubInput implements Input {
    private Input input;

    public StubInput(Input input) {
        this.input = input;
    }

    private int position = 0;
    private String[] answers;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public int ask(String question, int range) {
        boolean exists = false;
        int key = Integer.parseInt(answers[position++]);
        if (key > 0 && key <= range) {
            exists = true;
        }
        if (exists) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}
