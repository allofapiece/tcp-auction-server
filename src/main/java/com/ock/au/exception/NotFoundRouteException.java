package com.ock.au.exception;

public class NotFoundRouteException extends Exception {
    public NotFoundRouteException() {
        super("Illegal route");
    }

    public NotFoundRouteException(String message) {
        super(message);
    }
}
