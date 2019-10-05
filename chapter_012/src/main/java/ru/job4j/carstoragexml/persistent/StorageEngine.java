package ru.job4j.carstoragexml.persistent;

import ru.job4j.carstoragexml.models.Engine;

public interface StorageEngine {
    void add(Engine engine);

    void update(String oldEngineType, String newEngineType);

    void delete(Engine engine);
}
