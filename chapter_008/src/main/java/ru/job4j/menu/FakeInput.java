package ru.job4j.menu;

import java.util.List;

public class FakeInput implements Input {

    private final List<String> answers;
    private int count;

    public FakeInput(List<String> answers) {
        this.answers = answers;
    }
    @Override
    public String askUser(String question) {
        return answers.get(count++);
    }
}
