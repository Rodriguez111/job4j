package ru.job4j.carstorageannot.persistent;

import ru.job4j.carstorageannot.models.Transmission;

public interface StorageTransmission {
    void add(Transmission transmission);

    void update(String oldTransmissionType, String newTransmissionType);

    void delete(Transmission transmission);
}
