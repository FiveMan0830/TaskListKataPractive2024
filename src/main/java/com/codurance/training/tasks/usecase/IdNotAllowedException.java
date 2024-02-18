package com.codurance.training.tasks.usecase;

public class IdNotAllowedException extends RuntimeException {
    public static final String ID_NOT_ALLOWED = "Id with special characters is not allowed: \"%s\".";

    public IdNotAllowedException() {
    }

    public IdNotAllowedException(String message) {
        super(message);
    }

    public IdNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotAllowedException(Exception e) {
        super(e);
    }
}
