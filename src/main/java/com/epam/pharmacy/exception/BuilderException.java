package com.epam.pharmacy.exception;

public class BuilderException extends Exception {
    public BuilderException() {
    }

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Throwable e) {
        super(message, e);
    }
}
