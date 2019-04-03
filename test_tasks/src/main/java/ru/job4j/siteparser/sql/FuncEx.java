package ru.job4j.siteparser.sql;

public interface FuncEx<T, R> {

    R apply(T type) throws Exception;

}
