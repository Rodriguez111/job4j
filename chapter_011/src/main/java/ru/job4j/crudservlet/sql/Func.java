package ru.job4j.crudservlet.sql;

public interface Func<T, R> {
    R apply(T t) throws Exception;
}
