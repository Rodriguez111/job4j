package ru.job4j.siteparser.sql;

public interface TripleConsumeEx<A, B, C> {
    void accept(A a, B b, C c) throws Exception;
}
