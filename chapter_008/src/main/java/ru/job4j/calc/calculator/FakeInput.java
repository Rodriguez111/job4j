package ru.job4j.calc.calculator;

import java.util.List;
import java.util.Optional;

public class FakeInput implements Input {

    private final List<String> answers;
    private int count;

    public FakeInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public Optional<Double> getFirstValue(String question) {
        Optional<Double> value = Optional.empty();
        String stringValue = answers.get(count++);
        if (!stringValue.equals("")) {
            value = Optional.of(Double.valueOf(stringValue));
        }

        return value;
    }

    @Override
    public double getSecondValue(String question) {
        return Double.valueOf(answers.get(count++));
    }

    @Override
    public String getOperation(String question) {
        return answers.get(count++);
    }

    @Override
    public void setMenu(CalcMenu menu) {

    }
}
