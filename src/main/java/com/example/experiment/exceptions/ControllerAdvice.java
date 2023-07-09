package com.example.experiment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ResponseHandler> getResponseNotFoundResponse(ResourceNotFoundException resourceNotFoundException){
        ResponseHandler responseHandler = new ResponseHandler(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responseHandler,HttpStatus.NOT_FOUND);
    }
}
