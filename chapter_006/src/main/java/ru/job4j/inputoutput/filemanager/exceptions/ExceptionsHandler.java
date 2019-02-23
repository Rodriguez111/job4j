package ru.job4j.inputoutput.filemanager.exceptions;

public class ExceptionsHandler<T> {

    public void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T handleExceptionSupplier(SupplierTypeExceptionHandler<T> exceptionHandler) {
        T result = null;
        try {
            result = exceptionHandler.handleException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
