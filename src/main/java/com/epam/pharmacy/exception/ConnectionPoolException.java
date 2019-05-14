package com.epam.pharmacy.exception;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable e) {
        super(message, e);
    }
}
