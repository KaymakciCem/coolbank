package com.assignment.coolbank.exception;

public class RepaymentInvalidDateException extends RuntimeException{
    private String message;

    public RepaymentInvalidDateException(String message) {
        super(message);
        this.message = message;
    }
}
