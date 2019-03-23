package ru.job4j.trackersql.exeptions;

public interface BiConsumerEx<A, B> {
    /**
     * Accept two args.
     * @param a first.
     * @param b second.
     * @throws Exception possible exception.
     */
    void accept(A a, B b) throws Exception;
}
