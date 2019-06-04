package ru.job4j.cash.ver1.cashmakers;

import java.lang.ref.SoftReference;
import java.util.Map;

public interface CashMaker<T> {
    Map<String, SoftReference<T>> cash();
}
