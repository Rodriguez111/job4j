package ru.job4j.carstoragexml.persistent;

import ru.job4j.carstoragexml.models.Car;

public interface StorageCar {
    void add(Car car);

    void update(Car car);

    void delete(Car car);
}
