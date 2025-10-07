package com.api.fleche.model.exception;

public class UserNotFounException extends RuntimeException {
    public UserNotFounException(String message) {
        super(message);
    }
}
