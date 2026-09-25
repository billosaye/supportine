package com.example.supportline.domain.exception;


public class InvalidTicketStateException extends RuntimeException {

    public InvalidTicketStateException(String message) {
        super(message);
    }
}