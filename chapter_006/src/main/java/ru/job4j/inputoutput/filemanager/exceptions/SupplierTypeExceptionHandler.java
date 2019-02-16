package ru.job4j.inputoutput.filemanager.exceptions;

public interface SupplierTypeExceptionHandler<T> {
   T handleException() throws Exception;
}
