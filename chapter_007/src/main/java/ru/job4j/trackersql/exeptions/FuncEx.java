package ru.job4j.trackersql.exeptions;

public interface FuncEx<T, R> {
    R apply(T parameter) throws Exception;
}
