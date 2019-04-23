package ru.job4j.calculator;

import java.util.Optional;

public interface Input {
    Optional<Double> getFirstValue(String question);

    double getSecondValue(String question);

    String getOperation(String question);
}
