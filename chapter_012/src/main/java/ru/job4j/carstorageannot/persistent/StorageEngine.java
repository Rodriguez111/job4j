package ru.job4j.carstorageannot.persistent;

import ru.job4j.carstorageannot.models.Engine;

public interface StorageEngine {
    void add(Engine engine);

    void update(String oldEngineType, String newEngineType);

    void delete(Engine engine);
}
