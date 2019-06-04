package ru.job4j.cash.ver1;

import ru.job4j.cash.ver1.cashmakers.CashMaker;
import ru.job4j.cash.ver1.exceptions.CashReadFailedException;
import ru.job4j.cash.ver1.resulthandlers.SearchResultHandler;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.BiFunction;

/**
 * Cashes some data (param T), searches data by key (S - type of the key), returns data from found entity (R - type of returning data)
 * @param <S> - Type of cache search parameter.
 * @param <T> - cached entity type.
 * @param <R> - the type of data that the program should return.
 */

public class GetDataFromCash<S, T, R> implements GetFromCash<S, T, R> {

    private Map<String, SoftReference<T>> cashedData;

    private final CashMaker cashMaker;
    private final BiFunction<S, Map<String, SoftReference<T>>, Optional<T>> cashSearcher;
    private final SearchResultHandler<T, R> searchResultHandler;


    public GetDataFromCash(CashMaker cashMaker, BiFunction<S, Map<String, SoftReference<T>>, Optional<T>> cashSearcher, SearchResultHandler searchResultHandler) {
        this.cashMaker = cashMaker;
        this.cashSearcher = cashSearcher;
        this.searchResultHandler = searchResultHandler;
        cashData();
    }

    /**
     * Cashing data.
     */

    @Override
    public void cashData() {
        this.cashedData = this.cashMaker.cash();
    }

    /**
     * Searching entity in ru.job4j.cash by key.
     * @param searchKey - key for searching.
     * @return - found entity.
     */

    @Override
    public Optional<T> searchData(S searchKey) {
        Optional<T> result = cashSearcher.apply(searchKey, this.cashedData);

        if (result.isEmpty()) {
            cashData();
            result = cashSearcher.apply(searchKey, this.cashedData);
        }
        return result;
    }

    /**
     * Returns data from found entity (for example text from text file).
     * @param searchKey - key for searching.
     * @return - data read from the found entity.
     */

    @Override
    public R getData(S searchKey) {
        Optional<T> result = searchData(searchKey);
        if (result.isEmpty()) {
            result = searchData(searchKey);
        }
        if (result.isEmpty()) {
            throw new CashReadFailedException("Attempt to read data from cache failed");
        }
        return searchResultHandler.handleResult(result.get());
    }
}
