package com.sprink.api.exception;

public class MenuItemNotFoundException extends Exception {
    public MenuItemNotFoundException() {
    }

    public MenuItemNotFoundException(String message) {
        super(message);
    }

    public MenuItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
