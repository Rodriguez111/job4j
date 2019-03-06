package ru.job4j.filesearch.exceptions;


public class ExceptionsHandler {

    public void handleException(ExceptionHandler exceptionHandler) {
        try {
            exceptionHandler.handle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
