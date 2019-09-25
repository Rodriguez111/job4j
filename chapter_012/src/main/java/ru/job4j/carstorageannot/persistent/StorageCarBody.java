package ru.job4j.carstorageannot.persistent;

import ru.job4j.carstorageannot.models.CarBody;

public interface StorageCarBody {
    void add(CarBody carBody);

    void update(String oldBodyType, String bodyType);

    void delete(CarBody carBody);
}
