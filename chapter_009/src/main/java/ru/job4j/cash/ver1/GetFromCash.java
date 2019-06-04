package ru.job4j.cash.ver1;

import java.util.Optional;

public interface GetFromCash<S, T, R> {

    void cashData();

    Optional<T> searchData(S searchKey);

    R getData(S searchKey);

}
