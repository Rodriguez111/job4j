package ru.job4j.carstoragexml.persistent;

import ru.job4j.carstoragexml.models.CarBody;

public interface StorageCarBody {
    void add(CarBody carBody);

    void update(String oldBodyType, String bodyType);

    void delete(CarBody carBody);
}
