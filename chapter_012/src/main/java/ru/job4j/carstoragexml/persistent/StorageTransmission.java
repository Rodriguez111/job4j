package ru.job4j.carstoragexml.persistent;

import ru.job4j.carstoragexml.models.Transmission;

public interface StorageTransmission {
    void add(Transmission transmission);

    void update(String oldTransmissionType, String newTransmissionType);

    void delete(Transmission transmission);
}
