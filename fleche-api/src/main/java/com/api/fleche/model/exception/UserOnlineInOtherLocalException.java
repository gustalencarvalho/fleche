package com.api.fleche.model.exception;

public class UserOnlineInOtherLocalException extends RuntimeException {
    public UserOnlineInOtherLocalException(String message) {
        super(message);
    }
}
