package com.enigma.exceptions;

public class TaskNotFoundException extends Exception {

    private String message;

    public TaskNotFoundException() {

    }

    public TaskNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
