package ru.job4j.trackersql.exeptions;

public interface TripleConsumerEx<A, B, C> {
    void accept(A a, B b, C c) throws Exception;
}
