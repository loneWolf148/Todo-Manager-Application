package com.lonewolf.backend.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TodoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus errorStatus;

    public TodoException(String message, HttpStatus errorStatus) {
        super(message);
        this.errorStatus = errorStatus;
    }
}