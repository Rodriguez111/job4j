package ru.job4j.carstorageannot.persistent;

import ru.job4j.carstorageannot.models.Car;

public interface StorageCar {
    void add(Car car);

    void update(Car car);

    void delete(Car car);
}
