package ru.job4j.cash.ver1.resulthandlers;

public interface SearchResultHandler<T, R> {
    R handleResult(T searchResult);
}
