package com.directory.exception;

public enum Responses {
    SUCCESS("Success!"),
    INCORRECT_INPUT("Fail! Incorrect input data."),
    NOT_FOUND("Fail! Not found by the ID.");

    private final String message;

    Responses(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
