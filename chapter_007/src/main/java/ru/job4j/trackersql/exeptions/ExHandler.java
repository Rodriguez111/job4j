package ru.job4j.trackersql.exeptions;


public class ExHandler<T> {

    public T exHandle(UnaryEx unaryEx)  {
        T result = null;
        try {
            result = (T) unaryEx.exHandle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void exHandle(RunEx consumerExceptionable) {
        try {
            consumerExceptionable.exHandle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
