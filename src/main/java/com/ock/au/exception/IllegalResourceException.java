package com.ock.au.exception;

public class IllegalResourceException extends Exception {
    public IllegalResourceException() {
        super("Illegal resource");
    }

    public IllegalResourceException(String message) {
        super(message);
    }
}
