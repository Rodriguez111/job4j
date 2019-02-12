package ru.job4j.inputoutput.chat.exceptions;

public interface SupplierException<T> {
    T action() throws Exception;
}
