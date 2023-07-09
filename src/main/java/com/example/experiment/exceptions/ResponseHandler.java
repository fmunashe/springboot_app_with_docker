package com.example.experiment.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseHandler {
    private final String message;
    private final HttpStatus httpStatus;

    public ResponseHandler(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
