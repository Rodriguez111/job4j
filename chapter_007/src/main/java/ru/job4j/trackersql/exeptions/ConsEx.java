package ru.job4j.trackersql.exeptions;

public interface ConsEx<T> {
    void accept(T param) throws  Exception;
}
