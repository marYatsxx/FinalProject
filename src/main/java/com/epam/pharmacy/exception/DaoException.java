package com.epam.pharmacy.exception;

public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
