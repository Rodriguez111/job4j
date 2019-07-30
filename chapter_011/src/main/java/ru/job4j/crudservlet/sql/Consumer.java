package ru.job4j.crudservlet.sql;

public interface Consumer<T> {
    void accept(T t) throws Exception;

}
